package com.joker.mvvm.kotlin.basemvvm.lifecycle

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.joker.mvvm.kotlin.basemvvm.view.AppManager

/**
 * @Author joker
 * @Date 2020-04-27-16:35
 */
class AppManagerLifecyclerManager {
    companion object{
        fun bindAppManager(lifecycleOwner: LifecycleOwner,activity: Activity) {
            AppManagerLifecycle(lifecycleOwner,activity)
        }
    }

     class AppManagerLifecycle(lifecycleOwner: LifecycleOwner, private val activity: Activity) :LifecycleObserver {

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun bindAppManager() {
            AppManager.addActivity(activity)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun unBindAppManager(){
            AppManager.finishActivity(activity)
        }


    }

}