package com.joker.mvvm.kotlin.basemvvm.view

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

object ScreenUtils {

    fun isShouldHideInput(v: View?, event:MotionEvent):Boolean {
        if (v !=null &&(v is EditText)) {
            var leftTop = intArrayOf(0,0)
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)

        }
        return false
    }

}