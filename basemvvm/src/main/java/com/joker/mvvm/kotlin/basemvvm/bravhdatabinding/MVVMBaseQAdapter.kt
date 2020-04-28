package com.joker.mvvm.kotlin.basemvvm.bravhdatabinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @Author joker
 * @Date 2020-04-27-20:15
 */
abstract class MVVMBaseQAdapter<T,D:ViewDataBinding, K:BaseViewHolder>:BaseQuickAdapter<T,K> {

    constructor(data:List<T>):super(data)
    constructor(layoutId:Int,data:List<T>):super(layoutId,data)
    constructor(layoutId:Int):super(layoutId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K =
        if (viewType != LOADING_VIEW && viewType != HEADER_VIEW && viewType != EMPTY_VIEW && viewType != FOOTER_VIEW) {
            val d:D = DataBindingUtil.inflate(LayoutInflater.from(parent.context),this.mLayoutResId,null,false)
            d.executePendingBindings()
            val mvvmViewHolder = MVVMViewHolder<D,K>(d)
            mvBindViewClickListener(mvvmViewHolder )
            mvvmViewHolder.setMVVMAdapter(this@MVVMBaseQAdapter as BaseQuickAdapter<D, K>)
            mvvmViewHolder as K
        }else {
             super.onCreateViewHolder(parent, viewType)
        }

     private fun mvBindViewClickListener(mvvmViewHolder: BaseViewHolder) {
         val itemView = mvvmViewHolder.itemView
         if (onItemChildClickListener != null) {
            itemView.setOnClickListener { onItemClickListener.onItemClick(this@MVVMBaseQAdapter,it,mvvmViewHolder.layoutPosition - headerLayoutCount) }
        }
         if (onItemLongClickListener != null){
             itemView.setOnLongClickListener { onItemLongClickListener.onItemLongClick(this@MVVMBaseQAdapter,it,mvvmViewHolder.layoutPosition - headerLayoutCount) }
         }

    }



}