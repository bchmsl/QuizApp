package com.space.main.presentation.ui.ui_points.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.common.base.adapter.BaseAdapter
import com.space.common.util.C
import com.space.corecommon.databinding.ItemSubjectBinding
import com.space.main.presentation.model.user.UserSubjectUiModel

class UserSubjectsAdapter :
    BaseAdapter<UserSubjectUiModel>() {
    override fun createVH(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UserSubjectUiModel> {
        return QuizUserSubjectsViewHolder(
            ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class QuizUserSubjectsViewHolder(private val binding: ItemSubjectBinding) :
        BaseViewHolder<UserSubjectUiModel>(binding) {
        override fun onBind(
            item: UserSubjectUiModel,
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
