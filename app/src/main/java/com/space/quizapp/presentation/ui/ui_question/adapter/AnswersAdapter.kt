package com.space.quizapp.presentation.ui.ui_question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.databinding.QuizItemAnswerOptionBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class AnswersAdapter : BaseAdapter<QuizQuestionUiModel.QuizAnswerUiModel>() {

    override fun createVH(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel> {
        return AnswerViewHolder(
            QuizItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class AnswerViewHolder(private val binding: QuizItemAnswerOptionBinding) :
        BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel>(binding) {
        override fun onBind(
            item: QuizQuestionUiModel.QuizAnswerUiModel,
        ) {
            with(binding.root) {
                setContent(item.answerOption)
                setSelection(item.answerSelectedState)
            }
        }
    }
}
