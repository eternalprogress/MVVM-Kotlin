package com.joker.mvvm.kotlin.basemvvm.net.interceptor

import android.util.Log
import okhttp3.*
import okio.Buffer
import java.lang.Exception

/**
 * @Author joker
 * @Date 2020-04-27-14:09
 */
class HttpLogInterceptor:Interceptor {

    companion object{
        const val DEBUG = 1
        const val REALASE = 2
    }
    var level:Int = 2
    val tag = "MVK-Retrofit"
    override fun intercept(chain: Interceptor.Chain): Response {
        if (level==1) {
            //debug
            val request = chain.request()
            logForRequest(request)
            val response = chain.proceed(request)
            return logForResponse(response)
        }else {
            return chain.proceed(chain.request())

        }


    }

    /**
     * response打印
     */
    private fun logForResponse(response: Response): Response {
        try {
            loge("---------------------response log start-------------------------")
            val newBuilder = response.newBuilder()
            val clone = newBuilder.build()
            loge("url : ${clone.request().url()}")
            loge("code : ${clone.code()}")
            loge("protocol : ${clone.protocol()}")
            loge("Cookie : ${clone.headers("Set-Cookie")}")
            if (clone.message().isNotEmpty()) {
                loge("message : ${clone.message()}")
            }
            var body = clone.body()
            if (body != null) {
                val mediaType = body.contentType()
                if (mediaType != null) {
                    loge("contentType : $mediaType")
                    if (isText(mediaType)) {
                        val content = body.string()
                        loge("content : $content")
                        body = ResponseBody.create(mediaType,content)
                        return response.newBuilder().body(body).build()
                    }
                }
            }

        }catch (e:Exception) {
            loge(e.message?:"未知异常")
        }finally {
            loge("---------------------response log end-------------------------")
        }

        return response
    }

    /**
     * request打印
     */
    private fun logForRequest(request: Request) {
        try {
            val url = request.url().toString()
            val headers = request.headers()
            loge("---------------------request log start-------------------------")
            loge("method : ${request.method()}")
            loge("url : $url")
            if (headers.size() > 0) {
                loge("headers: \n")
                loge(headers.toString())
            }

            val body = request.body()
            if (body != null) {
                val contentType = body.contentType()
                if (contentType != null) {
                    loge("content : $contentType")
                    if (isText(contentType)) {
                        loge("content : ${bodyToString(request)}")
                    }else {
                        loge("content :  maybe [file part] , too large too print , ingnored!")
                    }

                }
            }
        }catch (e:Exception) {
            loge(e.message?:"未知异常")
        }finally {
            loge("---------------------request log end-------------------------")
        }



    }

    private fun bodyToString(request: Request): String {
        return try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body()!!.writeTo(buffer)
            buffer.readUtf8()

        } catch (e: Exception) {
            "something error when show requestBody."
        }
    }

    private fun isText(contentType: MediaType): Boolean {
        if (contentType.type() == "text") {
            return true
        }
        if (contentType.toString() == "application/x-wwww-form-urlencoded" ||
                contentType.subtype() == "json"  ||
                contentType.subtype() == "xml" ||
                contentType.subtype() == "html" ||
                contentType.subtype() == "webviewhtml") {
            return true
        }
        return false
    }

    private fun loge(log:String) {
        Log.e(tag,log )
    }
}