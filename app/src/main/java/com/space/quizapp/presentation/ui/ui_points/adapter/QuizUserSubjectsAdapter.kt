package com.space.quizapp.presentation.ui.ui_points.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.common.util.C
import com.space.quizapp.databinding.QuizItemSubjectBinding
import com.space.quizapp.presentation.base.adapter.BaseAdapter
import com.space.quizapp.presentation.model.user.QuizUserSubjectUiModel

class QuizUserSubjectsAdapter : BaseAdapter<QuizUserSubjectUiModel>() {
    override fun createVH(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QuizUserSubjectUiModel> {
        return QuizUserSubjectsViewHolder(
            QuizItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class QuizUserSubjectsViewHolder(private val binding: QuizItemSubjectBinding) :
        BaseViewHolder<QuizUserSubjectUiModel>(binding) {
        override fun onBind(
            item: QuizUserSubjectUiModel,
        ) {
            with(binding.root) {
                setContent(item.quizTitle, item.quizDescription, item.quizIcon)
                setPointsCount(item.score)
                if (item.score == item.maxScore) {
                    setColor(C.success_lighter)
                } else {
                    setColor(C.neutral_03_light_grey)
                }
            }
        }
    }
}