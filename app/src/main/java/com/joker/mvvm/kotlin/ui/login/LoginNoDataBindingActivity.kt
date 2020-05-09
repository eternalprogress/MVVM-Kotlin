package com.joker.mvvm.kotlin.ui.login

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.joker.mvvm.kotlin.R
import com.joker.mvvm.kotlin.base.MyBaseActivity
import com.joker.mvvm.kotlin.data.viewmodel.LoginViewModel
import com.joker.mvvm.kotlin.net.bean.User
import com.joker.mvvm.kotlin.ui.recyclerview.RecyclerViewActivity
import kotlinx.android.synthetic.main.activity_login_no_data_binding.*

class LoginNoDataBindingActivity : MyBaseActivity() {
    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context,LoginNoDataBindingActivity::class.java))
        }
    }
    lateinit var viewModel: LoginViewModel
    override fun getContentLayoutId(): Int = R.layout.activity_login_no_data_binding

    override fun initViewModel(): ViewModel? {
         viewModel = getViewModel(LoginViewModel::class.java)
        return viewModel
    }

    override fun initWidget() {
        btLogin.setOnClickListener {
            viewModel.getUser(etName.text.toString())
        }
    }
    override fun initData() {
        viewModel.user.observe(this, Observer<User> {
            Log.e("======","${it.name}  login")

            if (it.name == "bo") {
                RecyclerViewActivity.start(this)
                finish()
            }
        })
    }

}
