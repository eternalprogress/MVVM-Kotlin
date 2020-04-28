package com.joker.mvvm.kotlin.ui

import androidx.lifecycle.ViewModel
import com.joker.mvvm.kotlin.R
import com.joker.mvvm.kotlin.base.MyBaseActivity
import com.joker.mvvm.kotlin.data.viewmodel.LoginViewModel
import com.joker.mvvm.kotlin.databinding.ActivityLoginBinding

class LoginActivity : MyBaseActivity<ActivityLoginBinding>() {
     override fun getContentLayoutId(): Int = R.layout.activity_login

    override fun initViewModel(): ViewModel? {
        val viewModel = getViewModel(LoginViewModel::class.java)
        viewDataBinding.loginModel = viewModel
        return viewModel
    }


}
