package com.joker.mvvm.kotlin.basemvvm.ext

/**
 * @Author joker
 * @Date 2020/5/9-19:31
 */
sealed class BooleanExt<out T>
object OtherWise:BooleanExt<Nothing>()
class WithData<T>(val data:T):BooleanExt<T>()


inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> WithData(block())
        else -> OtherWise
    }


inline fun<T> Boolean.no(block: () -> T) =
    when{
        this -> OtherWise
        else -> WithData(block())
    }

inline fun <T> BooleanExt<T>.otherWise(block: () -> T):T  =
    when(this) {
        is WithData -> this.data
        is OtherWise -> block()
    }
