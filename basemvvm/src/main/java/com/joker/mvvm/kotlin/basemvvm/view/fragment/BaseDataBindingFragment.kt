package com.joker.mvvm.kotlin.basemvvm.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joker.mvvm.kotlin.basemvvm.event.BaseActionEvent
import com.joker.mvvm.kotlin.basemvvm.lifecycle.EventBusLifecyclerManager
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseAndroidViewModel
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseViewModel

/**
 * @Author joker
 * @Date 2020-04-27-17:42
 */
abstract class BaseDataBindingFragment<T:ViewDataBinding>:Fragment() {

    lateinit var viewDataBinding: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initArgs(arguments)
    }
     var mRoot:View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRoot == null) {
            val layId = getContentLayoutId()
            viewDataBinding = DataBindingUtil.inflate(inflater,layId,container,false)
            viewDataBinding.lifecycleOwner = this
            mRoot = viewDataBinding.root
            initViewModelEvent()
            initWidget()
        }else {
            if (mRoot!!.parent != null) {
                (mRoot!!.parent as ViewGroup).removeView(mRoot)
            }
        }
        return mRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //当view创建完成后初始化数据
        if (needEventBus()) {
            EventBusLifecyclerManager.bindAppManager(this,this)
        }
        initData()
    }

    open fun needEventBus(): Boolean = false

    open fun initData() {

    }


    open fun initWidget() {

    }

    abstract fun getContentLayoutId(): Int

    open fun initArgs(arguments: Bundle?) {

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
                            activity?.finish()
                        }
                        BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                            activity?.setResult(Activity.RESULT_OK)
                            activity?.finish()
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
                            activity?.finish()
                        }
                        BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                            activity?.setResult(Activity.RESULT_OK)
                            activity?.finish()
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


}