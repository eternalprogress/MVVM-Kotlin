package com.joker.mvvm.kotlin.basemvvm.net

import com.joker.mvvm.kotlin.basemvvm.net.exception.BaseException
import com.joker.mvvm.kotlin.basemvvm.net.model.IBaseResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

open class BaseRepository {
    suspend inline fun <T: Any, D:IBaseResponse<T>> apiCall(crossinline call: suspend() -> D) : T {
        return withContext(IO) { call.invoke() }.run {
            // 特殊处理
            if (!isSuccess()) {
                throw BaseException(code(),message())
            }
            data()
        }
    }
}