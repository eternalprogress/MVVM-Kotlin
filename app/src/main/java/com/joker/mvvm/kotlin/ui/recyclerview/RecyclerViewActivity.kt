package com.joker.mvvm.kotlin.ui.recyclerview

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.joker.mvvm.kotlin.R
import com.joker.mvvm.kotlin.base.MyBaseDBActivity
import com.joker.mvvm.kotlin.data.viewmodel.RvListViewModel
import com.joker.mvvm.kotlin.databinding.ActivityRecyclerViewBinding
import com.joker.mvvm.kotlin.ui.recyclerview.adapter.ReAdapter

class RecyclerViewActivity : MyBaseDBActivity<ActivityRecyclerViewBinding>() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, RecyclerViewActivity::class.java))
        }
    }

    private val viewModel by lazy {
        getViewModel(RvListViewModel::class.java)
    }
    private val list by lazy {
        ArrayList<String>()
    }
    private val adapter = ReAdapter(list)

    override fun getContentLayoutId(): Int = R.layout.activity_recycler_view

    override fun initViewModel(): ViewModel? {
        viewDataBinding.listViewModel = viewModel
        return viewModel
    }

    override fun initWidget() {
        super.initWidget()
        viewDataBinding.recyclerView.adapter = adapter
        viewModel.list.observe(this, Observer {
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }

    override fun initData() {
        super.initData()
        viewModel.getList(0)
    }



}
