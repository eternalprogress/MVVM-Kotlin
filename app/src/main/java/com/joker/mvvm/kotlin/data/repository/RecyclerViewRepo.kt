package com.joker.mvvm.kotlin.data.repository

import com.joker.mvvm.kotlin.base.MyRetrofitManager
import com.joker.mvvm.kotlin.basemvvm.net.BaseRepository

/**
 * @Author joker
 * @Date 2020-04-29-20:51
 */
class RecyclerViewRepo:BaseRepository() {
    suspend fun getList(page:Int):MutableList<String> = apiCall { MyRetrofitManager.service. getList(page)}

}