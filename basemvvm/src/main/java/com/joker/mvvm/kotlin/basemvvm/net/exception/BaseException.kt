package com.joker.mvvm.kotlin.basemvvm.net.exception

import java.lang.RuntimeException

open class BaseException(val code:Int, val errorMessage:String) :RuntimeException(errorMessage)