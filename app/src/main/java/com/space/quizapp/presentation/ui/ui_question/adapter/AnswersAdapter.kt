package com.space.quizapp.presentation.ui.ui_question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.databinding.QuizItemAnswerOptionBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.model.quiz.QuizAnswerUiModel

class AnswersAdapter : BaseAdapter<QuizAnswerUiModel>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuizAnswerUiModel> =
        AnswerViewHolder(
            QuizItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class AnswerViewHolder(private val binding: QuizItemAnswerOptionBinding) :
        BaseViewHolder<QuizAnswerUiModel>(binding) {
        override fun onBind(
            item: QuizAnswerUiModel,
            onClickCallback: ((QuizAnswerUiModel) -> Unit)?
        ) {
            binding.root.setContent(item.answerOption)
            binding.root.setSelection(item.selectedState)
        }
    }

}
