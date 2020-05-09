package com.joker.mvvm.kotlin.mmkvtest

import com.joker.mvvm.kotlin.basemvvm.ext.MMKVProperty

/**
 * @Author joker
 * @Date 2020/5/9-21:22
 */
object Setting {
    var name:String by MMKVProperty("name","")
    var age:Int by MMKVProperty("age",10)
}