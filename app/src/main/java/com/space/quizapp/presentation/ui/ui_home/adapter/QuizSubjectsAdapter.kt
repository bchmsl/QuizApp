package com.space.quizapp.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.common.base.adapter.QuizBaseItemCallback
import com.space.common.model.subject.presentation.QuizSubjectUiModel
import com.space.quizapp.databinding.QuizItemSubjectBinding

class QuizSubjectsAdapter :
    ListAdapter<com.space.common.model.subject.presentation.QuizSubjectUiModel, QuizSubjectsAdapter.SubjectsViewHolder>(com.space.common.base.adapter.QuizBaseItemCallback<com.space.common.model.subject.presentation.QuizSubjectUiModel>()) {


    private var itemClickListener: ((com.space.common.model.subject.presentation.QuizSubjectUiModel) -> Unit)? = null
    fun onItemClickListener(itemClickListener: ((com.space.common.model.subject.presentation.QuizSubjectUiModel) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        return SubjectsViewHolder(
            QuizItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.onBind(getItem(position), itemClickListener)
    }

    class SubjectsViewHolder(private val binding: QuizItemSubjectBinding) :
        ViewHolder(binding.root) {
        fun onBind(
            item: com.space.common.model.subject.presentation.QuizSubjectUiModel,
            itemClickListener: ((com.space.common.model.subject.presentation.QuizSubjectUiModel) -> Unit)?,
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
