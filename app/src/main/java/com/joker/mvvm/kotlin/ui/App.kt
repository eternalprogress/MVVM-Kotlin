package com.joker.mvvm.kotlin.ui

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV

/**
 * @Author joker
 * @Date 2020/5/9-19:15
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val dir = filesDir.absolutePath + "/mmkv_2"
        val rootDir = MMKV.initialize(dir)
        Log.e("MMKV", "mmkv root: $rootDir")
    }
}