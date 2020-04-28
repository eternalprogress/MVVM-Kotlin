package com.joker.mvvm.kotlin.basemvvm.databindadapter

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.joker.mvvm.kotlin.basemvvm.bravhdatabinding.ItemClickListener
import com.joker.mvvm.kotlin.basemvvm.bravhdatabinding.MVVMBaseQAdapter

/**
 * @Author joker
 * @Date 2020-04-27-21:52
 */

@BindingAdapter("android:bindLayoutManager")
fun bindLayoutManager(recyclerView: RecyclerView, manager: RecyclerView.LayoutManager) {
    recyclerView.layoutManager = manager
}

@BindingAdapter(value = ["android:adapter", "android:onChildItemClick"],requireAll = false)
fun setChildItemClick(
    recyclerView: RecyclerView,
    adapter: MVVMBaseQAdapter<*, ViewDataBinding, BaseViewHolder>,
    itemClickListener: ItemClickListener?
) {
    recyclerView.adapter = adapter
    if (itemClickListener != null) {
        adapter.setOnItemChildClickListener { _, _, position -> itemClickListener.onItemChildClick(position) }
    }
}

@BindingAdapter(value = ["android:bindLayoutManagerType","android:spanCount"],requireAll = false)
fun bindLayoutManager(recyclerView: RecyclerView,type:Int,spanCount:Int = 3) {
    val layoutManager = if(type ==1){
        LinearLayoutManager(recyclerView.context)
    }else {
        GridLayoutManager(recyclerView.context,spanCount)
    }
    recyclerView.layoutManager = layoutManager
}





