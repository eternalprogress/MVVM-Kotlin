package com.joker.mvvm.kotlin.ui.recyclerview.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.joker.mvvm.kotlin.R
import com.joker.mvvm.kotlin.databinding.ItemTestBinding

/**
 * @Author joker
 * @Date 2020-04-30-17:30
 */
class ReAdapter:BaseQuickAdapter<String,BaseDataBindingHolder<ItemTestBinding>> {


    constructor( data: MutableList<String>) : super(R.layout.item_test,data)


    override fun convert(holder: BaseDataBindingHolder<ItemTestBinding>, item: String) {
        holder.dataBinding?.setContent(item)
        holder.dataBinding?.executePendingBindings()
    }


}