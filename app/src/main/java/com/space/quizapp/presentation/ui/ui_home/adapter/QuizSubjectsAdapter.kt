package com.space.quizapp.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.databinding.QuizItemSubjectBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel

class QuizSubjectsAdapter : BaseAdapter<QuizSubjectUiModel>() {

    override fun createVH(parent: ViewGroup, viewType: Int): BaseViewHolder<QuizSubjectUiModel> {
        return SubjectsViewHolder(
            QuizItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class SubjectsViewHolder(private val binding: QuizItemSubjectBinding) :
        BaseViewHolder<QuizSubjectUiModel>(binding) {
        override fun onBind(
            item: QuizSubjectUiModel,
        ) {
            with(binding.root) {
                setContent(item.quizTitle, item.quizDescription, item.quizIcon)
                setPointsCount()
            }
        }
    }
}
