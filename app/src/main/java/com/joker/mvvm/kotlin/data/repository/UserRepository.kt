package com.joker.mvvm.kotlin.data.repository

import com.joker.mvvm.kotlin.base.MyRetrofitManager
import com.joker.mvvm.kotlin.basemvvm.net.BaseRepository
import com.joker.mvvm.kotlin.net.bean.User

/**
 * @Author joker
 * @Date 2020-04-28-11:53
 */
class UserRepository:BaseRepository() {
    suspend fun login(name:String):User = apiCall { MyRetrofitManager.service.login(name) }
}