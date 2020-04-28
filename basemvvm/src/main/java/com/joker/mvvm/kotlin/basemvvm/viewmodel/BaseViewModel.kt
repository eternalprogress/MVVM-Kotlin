package com.joker.mvvm.kotlin.basemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import com.joker.mvvm.kotlin.basemvvm.event.BaseActionEvent
import com.joker.mvvm.kotlin.basemvvm.net.exception.BaseException
import com.joker.mvvm.kotlin.basemvvm.net.exception.ERROR
import com.joker.mvvm.kotlin.basemvvm.net.exception.ExceptionHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import java.lang.Exception
import java.net.MalformedURLException
import java.text.ParseException

open class BaseViewModel : ViewModel(),IBaseViewModelAction {

     val actionLiveData by lazy {
        MutableLiveData<BaseActionEvent>()
    }


    override fun startLoading() {
        startLoading("加载中")

    }

    override fun startLoading(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG, message)
        actionLiveData.value = baseActionEvent
    }

    override fun dismissLoading() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
    }

    override fun showToast(message: String) {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.SHOW_TOAST, message)
    }

    override fun finish() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH)
    }

    override fun finishWithResultOk() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH_WITH_RESULT_OK)
    }

    override fun showError(code: Int, message: String) {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.SHOW_ERROE,code,message)
    }


    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在UI线程  并且自动处理错误 自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     */
    inline fun lauchUI(isShowDialog:Boolean = true,crossinline block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            block()
        }catch (e:Exception) {
            handleException(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
        }
    }


    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在UI线程  不会自动处理错误 不会自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     * @param error 失败的回调 在此方法体中处理自己的错误逻辑
     */
    inline fun lauchUIWithException(isShowDialog:Boolean = true,crossinline block:suspend CoroutineScope.() -> Unit,
                                    crossinline error:suspend CoroutineScope.(Exception) -> Unit = {}
    )  = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            block()
        } catch (e: Exception) {
            error(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
        }
    }

    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在UI线程  不会自动处理错误 不会自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     * @param error 失败的回调 在此方法体中处理自己的错误逻辑
     * @param complete 完成回调（无论成功失败都会调用）
     */
    inline fun lauchUIWithException(isShowDialog:Boolean = true,crossinline block:suspend CoroutineScope.() -> Unit,
                                    crossinline error:suspend CoroutineScope.(Exception) -> Unit = {},
                                    crossinline complete:suspend CoroutineScope.() -> Unit = {}
    )  = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            block()
        } catch (e: Exception) {
            error(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
            complete()
        }
    }



    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在IO线程  自动处理错误 自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     */
    inline fun lauchIO(isShowDialog:Boolean = true,crossinline block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            withContext(Dispatchers.IO) {
                block()
            }
        }catch (e:Exception) {
            handleException(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
        }
    }
    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在IO线程  不会自动处理错误 不会自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     * @param error 失败的回调 在此方法体中处理自己的错误逻辑
     */
    inline fun lauchIOWithException(isShowDialog:Boolean = true,crossinline  block:suspend CoroutineScope.() -> Unit,
                                    crossinline error:suspend CoroutineScope.(Exception) -> Unit = {}
    )  = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            withContext(Dispatchers.IO) {
                block()
            }
        } catch (e: Exception) {
            error(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
        }
    }

    /**
     *所有的网络请求都会在viewModelScope启动,当ViewModel销毁的时候 会自动取消所有协程
     * block执行在IO线程  不会自动处理错误 不会自动调用BaseActiviy的showError()方法
     * @param isShowDialog 是否显示dialog 默认true弹出
     * @param block 网络请求体
     * @param error 失败的回调 在此方法体中处理自己的错误逻辑
     * @param complete 完成回调（无论成功失败都会调用）
     */
    inline fun lauchIOWithException(isShowDialog:Boolean = true,crossinline block:suspend CoroutineScope.() -> Unit,
                                    crossinline error:suspend CoroutineScope.(Exception) -> Unit = {},
                                    crossinline complete:suspend CoroutineScope.() -> Unit = {}
    )  = viewModelScope.launch {
        try {
            if (isShowDialog) {
                startLoading()
            }
            withContext(Dispatchers.IO) {
                block()
            }
        } catch (e: Exception) {
            error(e)
        }finally {
            if (isShowDialog) {
                dismissLoading()
            }
            complete()
        }
    }

    /**
     * 处理错误,发送错误通知 调用Activity的showError方法
     */
    fun handleException(e: Exception) {
        val handleException = ExceptionHelper.handleException(e)
        showError(handleException.code,handleException.errorMessage)

    }


}