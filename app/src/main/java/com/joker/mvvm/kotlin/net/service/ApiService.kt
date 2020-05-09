package com.joker.mvvm.kotlin.net.service

import com.joker.mvvm.kotlin.base.BaseResult
import com.joker.mvvm.kotlin.net.bean.User
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author joker
 * @Date 2020-04-28-11:36
 */
interface ApiService {
    @GET("logintest/{name}")
    suspend fun login(@Path("name") name:String):BaseResult<User>

    @GET("recyclerview/{page}")
    suspend fun getList(@Path("page") page:Int):BaseResult<MutableList<String>>

}