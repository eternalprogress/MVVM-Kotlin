package com.joker.mvvm.kotlin.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.joker.mvvm.kotlin.R
import com.joker.mvvm.kotlin.base.MyBaseDBActivity
import com.joker.mvvm.kotlin.data.viewmodel.LoginViewModel
import com.joker.mvvm.kotlin.databinding.ActivityLoginBinding
import com.joker.mvvm.kotlin.net.bean.User
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity : MyBaseDBActivity<ActivityLoginBinding>() {


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun getContentLayoutId(): Int = R.layout.activity_login
    override fun needEventBus(): Boolean = true

    override fun initViewModel(): ViewModel? {
        val viewModel = getViewModel(LoginViewModel::class.java)
        viewDataBinding.loginModel = viewModel
        return viewModel
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginBack(user: User) {
        Log.e("======", "${user.name}  login")
        if (user.name == "joker") {
            LoginNoDataBindingActivity.start(this)
            finish()
        }
    }


}
