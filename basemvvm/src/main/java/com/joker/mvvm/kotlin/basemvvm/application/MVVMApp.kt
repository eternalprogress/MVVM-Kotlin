package com.joker.mvvm.kotlin.basemvvm.application

import android.app.Application
import android.content.ContextWrapper
import com.tencent.mmkv.MMKV

/**
 * @Author joker
 * @Date 2020/5/10-21:17
 */
private lateinit var INSTANCE:Application
open class MVVMApp:Application() {
    override fun onCreate() {
        super.onCreate()
        val dir = mmkvDir()
        MMKV.initialize(dir)
        INSTANCE = this
    }

    /**
     * 重写此方法可以自定义mmkv的目录
     */
    open fun mmkvDir() = filesDir.absolutePath + "/mvvm"

}
object AppContext:ContextWrapper(INSTANCE)