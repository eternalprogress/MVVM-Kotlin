package com.joker.mvvm.kotlin.basemvvm.view

import android.app.Activity
import java.util.*

object AppManager {
    val activityStack by lazy {
        Stack<Activity>()
    }

    fun  getActivity(cls:Class<*>): Activity? {
       for (activity in activityStack){
           if (activity.javaClass === cls) {
               return activity
           }
       }
        return null
    }

    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun currentActivity():Activity{
        val activity = activityStack.lastElement()
        return activity
    }

    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }


    fun finishActivity(activity: Activity) {
        if(activityStack.contains(activity)){
            activity.finish()
        }
    }




}