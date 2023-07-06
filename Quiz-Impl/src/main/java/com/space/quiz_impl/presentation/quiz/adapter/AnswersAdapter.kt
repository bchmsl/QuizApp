package com.space.quiz_impl.presentation.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.common.base.adapter.BaseAdapter
import com.space.quiz_impl.presentation.quiz.model.QuizQuestionUiModel
import com.space.quizimpl.databinding.QuizItemAnswerOptionBinding

class AnswersAdapter(var point: (() -> Int)? = null) :
    BaseAdapter<QuizQuestionUiModel.QuizAnswerUiModel>() {
    override fun createVH(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel> {
        return AnswerViewHolder(
            QuizItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), point
        )
    }

    class AnswerViewHolder(
        private val binding: QuizItemAnswerOptionBinding,
        private val point: (() -> Int)?
    ) :
        BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel>(binding) {
        override fun onBind(
            item: QuizQuestionUiModel.QuizAnswerUiModel,
        ) {
            with(binding.root) {
                setContent(item.answerOption)
                setSelection(item.answerSelectedState, point?.invoke())
            }
        }
    }
}
