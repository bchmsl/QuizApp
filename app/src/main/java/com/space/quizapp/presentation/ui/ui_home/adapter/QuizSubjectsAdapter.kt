package com.space.quizapp.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.quizapp.databinding.QuizItemSubjectBinding
import com.space.quizapp.presentation.base.adapter.QuizBaseItemCallback
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel

class QuizSubjectsAdapter :
    ListAdapter<QuizQuestionsUiModel, QuizSubjectsAdapter.SubjectsViewHolder>(QuizBaseItemCallback<QuizQuestionsUiModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder =
        SubjectsViewHolder(
            QuizItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class SubjectsViewHolder(private val binding: QuizItemSubjectBinding) :
        ViewHolder(binding.root) {
        fun onBind(model: QuizQuestionsUiModel) {
            with(binding) {
                root.setContent(model.quizTitle, model.quizDescription, model.quizIcon)
                root.setPointsCount()
            }
        }
    }
}
