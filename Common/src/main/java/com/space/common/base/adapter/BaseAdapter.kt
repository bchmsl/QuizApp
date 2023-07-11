package com.space.common.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<MODEL : Any> :
    ListAdapter<MODEL, BaseAdapter.BaseViewHolder<MODEL>>(BaseItemCallback<MODEL>()) {

    private var onClickCallback: ((MODEL) -> Unit)? = null

    abstract fun createVH(parent: ViewGroup, viewType: Int): BaseViewHolder<MODEL>

    fun onItemClickListener(onClickCallback: ((MODEL) -> Unit)?) {
        this.onClickCallback = onClickCallback
    }

    abstract class BaseViewHolder<MODEL : Any>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onBind(item: MODEL)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MODEL>, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MODEL> {
        val vh = createVH(parent, viewType)
        vh.itemView.setOnClickListener {
            onClickCallback?.invoke(getItem(vh.adapterPosition))
        }
        return vh
    }
}
