package com.joker.mvvm.kotlin.basemvvm.lifecycle

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.joker.mvvm.kotlin.basemvvm.view.AppManager
import org.greenrobot.eventbus.EventBus

/**
 * @Author joker
 * @Date 2020-04-27-16:35
 */
class EventBusLifecyclerManager {
    companion object{
        fun<T> bindAppManager(lifecycleOwner: LifecycleOwner,activity: T) {
            EventBusLifecycle(lifecycleOwner,activity)
        }
    }

     class EventBusLifecycle<T>(lifecycleOwner: LifecycleOwner, private val activity: T) :LifecycleObserver {

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun register() {
            Log.e("=====","注册了")
            EventBus.getDefault().register(activity)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun unregister(){
            Log.e("=====","unregister")
            EventBus.getDefault().unregister(activity)
        }


    }

}