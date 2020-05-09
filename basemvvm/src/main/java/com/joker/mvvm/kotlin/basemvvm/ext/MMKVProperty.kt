package com.joker.mvvm.kotlin.basemvvm.ext

import com.tencent.mmkv.MMKV
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @Author joker
 * @Date 2020/5/9-21:00
 */
class MMKVProperty<T>(val key:String, val default:T, val mmkv: MMKV = MMKV.defaultMMKV()):ReadWriteProperty<Any?,T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
       return findValue(key)
    }

    private fun findValue(key: String):T {
        with(mmkv) {
           return when(default) {
                is Boolean -> decodeBool(key,default)
                is ByteArray -> decodeBytes(key,default)
                is Double -> decodeDouble(key,default)
                is Float -> decodeFloat(key,default)
                is Int -> decodeInt(key,default)
                is Long -> decodeLong(key,default)
                is String -> decodeString(key,default)
                else -> throw IllegalArgumentException("Unsupported type")
            } as T
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
       mmkvput(key,value)
    }

    private fun mmkvput(key: String, value: T) {
        with(mmkv) {
            when(value) {
                is Boolean -> encode(key,value)
                is ByteArray -> encode(key,value)
                is Double -> encode(key,value)
                is Float -> encode(key,value)
                is Int -> encode(key,value)
                is Long -> encode(key,value)
                is String -> encode(key,value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }
}