package com.joker.mvvm.kotlin.basemvvm.viewmodel

interface IBaseViewModelAction {
    fun startLoading()
    fun startLoading(message: String)
    fun dismissLoading()

    fun showToast(message: String)

    fun finish()

    fun finishWithResultOk()

    fun showError(code:Int,message: String)

}