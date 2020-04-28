package com.joker.mvvm.kotlin.base

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.joker.mvvm.kotlin.basemvvm.view.activity.BaseDataBindingActivity

/**
 * @Author joker
 * @Date 2020-04-28-11:24
 */
abstract class MyBaseActivity<T:ViewDataBinding>:BaseDataBindingActivity<T>() {

    override fun showError(code: Int, message: String) {
        //根据业务实现错误回调  比如弹提示框
        showToast("code : $code, message : $message")
        Log.e("=======","error :code : $code, message : $message")
    }

    override fun startLoading(message: String) {
        //根据业务实现dialog
        Log.e("=======","根据业务实现dialog")
    }


    override fun dismissLoading() {
        //dialog消失
        Log.e("=======","dialog消失")

    }

}