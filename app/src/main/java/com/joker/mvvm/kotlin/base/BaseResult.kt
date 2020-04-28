package com.joker.mvvm.kotlin.base

import com.joker.mvvm.kotlin.basemvvm.net.model.IBaseResponse

/**
 * @Author joker
 * @Date 2020-04-28-11:29
 */
data class BaseResult<T>(val code:Int, val msg:String, val data:T):IBaseResponse<T> {

    override fun code(): Int = code

    override fun message(): String = msg

    override fun data(): T  = data

    override fun isSuccess(): Boolean= code ==1001
}