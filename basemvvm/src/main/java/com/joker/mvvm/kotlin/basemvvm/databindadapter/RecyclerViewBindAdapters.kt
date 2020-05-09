package com.joker.mvvm.kotlin.basemvvm.databindadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @Author joker
 * @Date 2020-04-27-21:52
 */
    @BindingAdapter("android:bindLayoutManager")
    fun bindLayoutManager(recyclerView: RecyclerView, manager: RecyclerView.LayoutManager) {
        recyclerView.layoutManager = manager
    }

    @BindingAdapter(value = ["android:bindLayoutManagerType", "android:spanCount"], requireAll = false)
    fun bindLayoutManager(recyclerView: RecyclerView, type: Int, spanCount: Int?) {
        val layoutManager = if (type == 1) {
            LinearLayoutManager(recyclerView.context)
        } else {
            GridLayoutManager(recyclerView.context, spanCount ?:3)
        }
        recyclerView.layoutManager = layoutManager
    }



