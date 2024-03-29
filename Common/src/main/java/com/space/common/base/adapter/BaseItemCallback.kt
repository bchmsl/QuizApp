package com.space.common.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback<MODEL : Any> : DiffUtil.ItemCallback<MODEL>() {
    override fun areItemsTheSame(oldItem: MODEL, newItem: MODEL): Boolean =
        oldItem === newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: MODEL, newItem: MODEL): Boolean =
        oldItem == newItem
}
