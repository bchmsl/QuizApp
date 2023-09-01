package com.space.quiz_impl.presentation.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.common.base.adapter.BaseAdapter
import com.space.common.model.question.model.QuestionUiModel
import com.space.quizimpl.databinding.ItemAnswerOptionBinding

class AnswersAdapter(var point: (() -> Int)? = null) :
    BaseAdapter<QuestionUiModel.QuizAnswerUiModel>() {
    override fun createVH(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuestionUiModel.QuizAnswerUiModel> {
        return AnswerViewHolder(
            ItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), point
        )
    }

    class AnswerViewHolder(
        private val binding: ItemAnswerOptionBinding,
        private val point: (() -> Int)?
    ) :
        BaseViewHolder<QuestionUiModel.QuizAnswerUiModel>(binding) {
        override fun onBind(
            item: QuestionUiModel.QuizAnswerUiModel,
        ) {
            with(binding.root) {
                setContent(item.answerOption)
                setSelection(item.answerSelectedState, point?.invoke())
            }
        }
    }
}
