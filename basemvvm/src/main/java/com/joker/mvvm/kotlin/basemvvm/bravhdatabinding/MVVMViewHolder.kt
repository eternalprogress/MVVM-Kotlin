package com.joker.mvvm.kotlin.basemvvm.bravhdatabinding

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @Author joker
 * @Date 2020-04-27-19:55
 */
class MVVMViewHolder<T:ViewDataBinding,K:BaseViewHolder> (private val binding:T) :BaseViewHolder(binding.root){
    init {
        binding.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setMVVMAdapter(adapter: BaseQuickAdapter<T,K>):BaseViewHolder{
        super.setAdapter(adapter)
        return this
    }

    fun getDataViewBinding():T{
        return binding
    }



}