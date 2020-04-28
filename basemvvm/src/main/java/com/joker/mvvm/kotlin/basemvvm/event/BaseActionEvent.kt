package com.joker.mvvm.kotlin.basemvvm.event

class BaseActionEvent(val action:Int,val code:Int,val message:String) {

    constructor(action: Int) : this(action, 0,"")
    constructor(action: Int,message: String) : this(action, 0,message)
    companion object{
          const val SHOW_LOADING_DIALOG = 1
          const val DISMISS_LOADING_DIALOG = 2
          const val SHOW_TOAST = 3
          const val FINISH = 4
          const val FINISH_WITH_RESULT_OK = 5
          const val SHOW_ERROE = 6
    }
}