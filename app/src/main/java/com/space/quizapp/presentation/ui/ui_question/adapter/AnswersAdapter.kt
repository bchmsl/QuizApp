package com.space.quizapp.presentation.ui.ui_question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.databinding.QuizItemAnswerOptionBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel

class AnswersAdapter : BaseAdapter<AnswerModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AnswerModel> =
        AnswerViewHolder(
            QuizItemAnswerOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class AnswerViewHolder(private val binding: QuizItemAnswerOptionBinding) :
        BaseViewHolder<AnswerModel>(binding) {
        override fun onBind(item: AnswerModel, onClickCallback: ((AnswerModel) -> Unit)?) {
            binding.root.setContent(item.answerOption)
            binding.root.setSelection(item.selectedState)
        }
    }

}
