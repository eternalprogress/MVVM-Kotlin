package com.joker.mvvm.kotlin.basemvvm.net

import com.joker.mvvm.kotlin.basemvvm.BuildConfig
import com.joker.mvvm.kotlin.basemvvm.net.interceptor.HeaderInterceptor
import com.joker.mvvm.kotlin.basemvvm.net.interceptor.HttpLogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseretrofitClient {

    private val client:OkHttpClient
        get() {
            val okhttpClient = setOkhttpClient()
            if (okhttpClient == null) {
                val builder = OkHttpClient.Builder()
                //添加header
                val headerInterceptor = setHeaderInterceptor() ?: HeaderInterceptor()
                builder.addInterceptor(headerInterceptor)

                val logging:Interceptor

                if (setHttpLoggingInterceptor() == null) {
                    logging = HttpLogInterceptor()
                    if (BuildConfig.DEBUG) {
                        logging.level = HttpLogInterceptor.DEBUG
                    } else {
                        logging.level = HttpLogInterceptor.REALASE
                    }
                }else {
                    logging =  setHttpLoggingInterceptor()!!
                }
                builder.addInterceptor(logging)
                    .connectTimeout(setConnectTimeout().toLong(), TimeUnit.SECONDS)
                return builder.build()
            }else {
                return okhttpClient
            }
        }


    fun <T> getService(clazz: Class<T>, baseUrl: String): T {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build().create(clazz)
    }

    //重写设置okhttpClient 不重写使用默认
    open fun setOkhttpClient(): OkHttpClient? = null
    //重写设置headInterceptor 不重写使用默认
    open fun setHeaderInterceptor():Interceptor? = null
    //重写设置HttpLoggingInterceptor
    open fun setHttpLoggingInterceptor():Interceptor? = null
    //重写设置超时时间
    open fun setConnectTimeout():Int = 5

}