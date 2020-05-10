package com.joker.mvvm.kotlin.base

import android.util.Log
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.joker.mvvm.kotlin.basemvvm.ext.ankoext.AnkoLogger
import com.joker.mvvm.kotlin.basemvvm.ext.ankoext.error
import com.joker.mvvm.kotlin.basemvvm.view.activity.BaseDataBindingActivity

/**
 * @Author joker
 * @Date 2020-04-28-11:24
 */
abstract class MyBaseDBActivity<T:ViewDataBinding>:BaseDataBindingActivity<T>(),AnkoLogger {

    override fun showError(code: Int, message: String) {
        //根据业务实现错误回调  比如弹提示框
        Toast.makeText(this,"error :code : $code, message : $message",Toast.LENGTH_SHORT).show()
        error("error :code : $code, message : $message")
    }

    override fun startLoading(message: String) {
        //根据业务实现dialog
        Toast.makeText(this,"根据业务实现dialog",Toast.LENGTH_SHORT).show()

        error("根据业务实现dialog")
    }


    override fun dismissLoading() {
        //dialog消失
        Toast.makeText(this,"dialog消失",Toast.LENGTH_SHORT).show()
        error("dialog消失")
    }

}