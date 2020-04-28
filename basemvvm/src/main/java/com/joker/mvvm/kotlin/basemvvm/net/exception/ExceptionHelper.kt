package com.joker.mvvm.kotlin.basemvvm.net.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import javax.net.ssl.SSLException

/**
 * @Author joker
 * @Date 2020-04-27-19:12
 */
object ExceptionHelper {

        fun handleException(e: Exception) = when(e) {
                is HttpException ->{
                    BaseException(ERROR.HTTP_ERROR.getKey(),e.message())
                }
                is JsonParseException,is JSONException,is ParseException,is MalformedURLException -> {
                    BaseException(ERROR.PARSE_ERROR.getKey(),e.message ?:ERROR.PARSE_ERROR.getValue())
                }
                is ConnectException -> {
                    BaseException(ERROR.NETWORD_ERROR.getKey(),e.message ?:ERROR.NETWORD_ERROR.getValue())
                }

                is SSLException ->{
                   BaseException(ERROR.SSL_ERROR.getKey(),e.message ?:ERROR.SSL_ERROR.getValue())
                }

                is SocketTimeoutException,is UnknownHostException -> {
                    BaseException(ERROR.TIMEOUT_ERROR.getKey(),e.message ?:ERROR.SSL_ERROR.getValue())
                }
                is BaseException -> {
                    e
                }
                else -> {
                    BaseException(ERROR.UNKNOWN.getKey(), e.message?: ERROR.UNKNOWN.getValue())
                }
            }


}