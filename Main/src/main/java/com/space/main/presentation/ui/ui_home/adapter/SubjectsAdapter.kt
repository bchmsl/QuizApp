package com.space.main.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.common.base.adapter.BaseItemCallback
import com.space.common.model.question.model.SubjectUiModel
import com.space.corecommon.databinding.ItemSubjectBinding

class SubjectsAdapter :
    ListAdapter<SubjectUiModel, SubjectsAdapter.SubjectsViewHolder>(BaseItemCallback<SubjectUiModel>()) {


    private var itemClickListener: ((SubjectUiModel) -> Unit)? = null
    fun onItemClickListener(itemClickListener: ((SubjectUiModel) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.onBind(getItem(position), itemClickListener)
    }

    class SubjectsViewHolder(private val binding: ItemSubjectBinding) :
        ViewHolder(binding.root) {
        fun onBind(
            item: SubjectUiModel,
            itemClickListener: ((SubjectUiModel) -> Unit)?,
        ) {
            with(binding.root) {
                setContent(item.quizTitle, item.quizDescription, item.quizIcon)
                setPointsCount()
                setCustomClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }
}
