package com.joker.mvvm.kotlin.data.viewmodel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseViewModel
import com.joker.mvvm.kotlin.data.repository.UserRepository
import com.joker.mvvm.kotlin.net.bean.User
import org.greenrobot.eventbus.EventBus

/**
 * @Author joker
 * @Date 2020-04-28-12:02
 */
class LoginViewModel :BaseViewModel() {
    private val userRepo = UserRepository()
    val user by lazy{MutableLiveData<User>()}
    lateinit var userName:String
    fun getUser(name:String) {
        Log.e("=======","进来了")
        lauchUI {
            user.value = userRepo.login(name)
            //保存到本地
            Log.e("=======","${user.value}")
            //发送通知结束登录跳转界面
            EventBus.getDefault().post(user.value?.name ?:"null")
        }
    }

    fun nameInput(name:Editable) {
        userName = name.toString()
    }

}