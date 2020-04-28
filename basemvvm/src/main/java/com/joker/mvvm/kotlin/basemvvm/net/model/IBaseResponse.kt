package com.joker.mvvm.kotlin.basemvvm.net.model

interface IBaseResponse<T> {
    fun code():Int
    fun message():String
    fun data():T
    fun isSuccess():Boolean
}