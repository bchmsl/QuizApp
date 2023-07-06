package com.space.quizapp.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.common.base.adapter.QuizBaseItemCallback
import com.space.quizapp.databinding.QuizItemSubjectBinding
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel

class QuizSubjectsAdapter :
    ListAdapter<QuizSubjectUiModel, QuizSubjectsAdapter.SubjectsViewHolder>(com.space.common.base.adapter.QuizBaseItemCallback<QuizSubjectUiModel>()) {


    private var itemClickListener: ((QuizSubjectUiModel) -> Unit)? = null
    fun onItemClickListener(itemClickListener: ((QuizSubjectUiModel) -> Unit)?) {
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
            item: QuizSubjectUiModel,
            itemClickListener: ((QuizSubjectUiModel) -> Unit)?,
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
