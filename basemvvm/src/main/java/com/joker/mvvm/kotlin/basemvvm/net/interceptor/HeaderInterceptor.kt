package com.joker.mvvm.kotlin.basemvvm.net.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @Author joker
 * @Date 2020-04-27-13:54
 */
class HeaderInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
            .addHeader("Accept-Encoding","gzip")
            .addHeader("Accept","application/json")
            .addHeader("Content-Type","application/json; charset=utf-8")
            .method(request.method(),request.body())

        return chain.proceed(requestBuilder.build())
    }
}