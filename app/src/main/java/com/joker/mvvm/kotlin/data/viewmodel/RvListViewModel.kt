package com.joker.mvvm.kotlin.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.joker.mvvm.kotlin.basemvvm.viewmodel.BaseViewModel
import com.joker.mvvm.kotlin.data.repository.RecyclerViewRepo
import com.joker.mvvm.kotlin.ui.recyclerview.adapter.ReAdapter

/**
 * @Author joker
 * @Date 2020-04-29-21:09
 */
class RvListViewModel :BaseViewModel() {
    val list  =  MutableLiveData<MutableList<String>>()

    private val repo = RecyclerViewRepo()

    fun getList(page:Int) {
        lauchUI {
            list.value = repo.getList(page)
//            adapter.notifyDataSetChanged()
            Log.e("======","${list.value}")

        }
    }

    fun  itemClick(position:Int) {
        Log.e("======", list.value?.get(position) ?:"null")
    }




}