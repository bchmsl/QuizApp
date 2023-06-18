package com.space.quizapp.presentation.ui.ui_question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.databinding.QuizItemAnswerOptionBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel

class AnswersAdapter : BaseAdapter<QuizQuestionUiModel.QuizAnswerUiModel>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel> =
        AnswerViewHolder(
            QuizItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class AnswerViewHolder(private val binding: QuizItemAnswerOptionBinding) :
        BaseViewHolder<QuizQuestionUiModel.QuizAnswerUiModel>(binding) {
        override fun onBind(
            item: QuizQuestionUiModel.QuizAnswerUiModel,
            onClickCallback: ((QuizQuestionUiModel.QuizAnswerUiModel) -> Unit)?
        ) {
            with(binding.root) {
                setContent(item.answerOption)
                setSelection(item.selectedState)
                setOnClickListener {
                    onClickCallback?.invoke(item)
                }
            }
        }
    }
}
