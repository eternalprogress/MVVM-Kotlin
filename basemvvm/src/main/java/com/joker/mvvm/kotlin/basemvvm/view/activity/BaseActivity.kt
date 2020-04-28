package com.joker.mvvm.kotlin.basemvvm.view.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joker.mvvm.kotlin.basemvvm.event.BaseActionEvent
import com.joker.mvvm.kotlin.basemvvm.lifecycle.AppManagerLifecyclerManager
import com.joker.mvvm.kotlin.basemvvm.lifecycle.EventBusLifecyclerManager
import com.joker.mvvm.kotlin.basemvvm.view.ScreenUtils
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseAndroidViewModel
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {
    private var isNormal = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWindows()
        isNormal = initArgs(intent.extras)
        if (isNormal) {
            AppManagerLifecyclerManager.bindAppManager(this,this)
            setContentView(getContentLayoutId())
            if (needEventBus()) {
                EventBusLifecyclerManager.bindAppManager(this,this)
            }
            initViewModelEvent()
            initWidget()
            initData()
        }else{
            finish()
        }
    }

    open fun needEventBus(): Boolean =false
    /**
     * 得到当前文件的资源ID
     */
    abstract fun getContentLayoutId(): Int

    /**
     * 初始化相关参数
     * @param extras 参数Bundle
     * @return 如果参数正确返回true,错误返回false
     */
    open fun initArgs(extras: Bundle?): Boolean = true

    /**
     *初始化窗口
     */
    open fun initWindows() {

    }

    open  fun initData() {

    }

    open fun initWidget() {

    }

    private fun initViewModelEvent() {
        val viewModelList = initViewModelList()
        if (viewModelList != null && viewModelList.isNotEmpty()) {
            observeEvent(viewModelList)
        } else {
            val viewModel = initViewModel()
            if (viewModel != null) {
                val modelList = ArrayList<ViewModel>()
                modelList.add(viewModel)
                observeEvent(modelList)
            }
        }

    }

    private fun observeEvent(viewModelList: List<ViewModel>) {
        for (viewModel in viewModelList) {
            if (viewModel is BaseViewModel) {
                viewModel.actionLiveData.observe(this, Observer<BaseActionEvent> {
                    when (it.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> {
                            startLoading(it.message)
                        }
                        BaseActionEvent.DISMISS_LOADING_DIALOG -> {
                            dismissLoading()
                        }
                        BaseActionEvent.SHOW_TOAST -> {
                            showToast(it.message)

                        }
                        BaseActionEvent.FINISH -> {
                            finish()
                        }
                        BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        BaseActionEvent.SHOW_ERROE -> {
                            showError(it.code, it.message)
                        }

                    }


                })
            }

            if (viewModel is BaseAndroidViewModel) {
                viewModel.actionLiveData.observe(this, Observer<BaseActionEvent> {
                    when (it.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> {
                            startLoading(it.message)
                        }
                        BaseActionEvent.DISMISS_LOADING_DIALOG -> {
                            dismissLoading()
                        }
                        BaseActionEvent.SHOW_TOAST -> {
                            showToast(it.message)

                        }
                        BaseActionEvent.FINISH -> {
                            finish()
                        }
                        BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        BaseActionEvent.SHOW_ERROE -> {
                            showError(it.code, it.message)
                        }

                    }


                })
            }
        }


    }

    open fun showError(code: Int, message: String) {

    }

    open fun showToast(message: String) {

    }

    open fun dismissLoading() {

    }

    open fun startLoading(message: String) {

    }

    open fun initViewModelList(): List<ViewModel>? = null
    open fun initViewModel(): ViewModel? = null

    fun <T : ViewModel>  getViewModel(modelClass:Class<T>) = ViewModelProvider(this).get(modelClass)


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (ScreenUtils.isShouldHideInput(v, ev)) {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    v!!.windowToken,
                    0
                )
            }

            return super.dispatchTouchEvent(ev)
        }

        if (window.superDispatchTouchEvent(ev)) {
            return true
        }
        return onTouchEvent(ev)

    }


}