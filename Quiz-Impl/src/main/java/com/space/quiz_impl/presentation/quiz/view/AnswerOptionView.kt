package com.space.quiz_impl.presentation.quiz.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.common.extensions.utils.gone
import com.space.common.extensions.utils.setColorStateList
import com.space.common.extensions.utils.visible
import com.space.common.model.question.model.AnswerSelectedState
import com.space.quizimpl.databinding.ViewAnswerOptionBinding

class AnswerOptionView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {
    private val binding =
        ViewAnswerOptionBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setSelection(AnswerSelectedState.ANSWER_NEUTRAL)
    }

    fun setContent(title: String) {
        binding.optionTitleTextView.text = title
    }

    fun setSelection(selectState: AnswerSelectedState, pointAmount: Int? = null) {
        when (selectState) {
            AnswerSelectedState.ANSWER_SELECTED_POSITIVE -> selectedCorrect()
            AnswerSelectedState.ANSWER_SELECTED_NEGATIVE -> selectedIncorrect()
            AnswerSelectedState.ANSWER_NEUTRAL -> unselected()
            AnswerSelectedState.ANSWER_SELECTED_CORRECT -> selectedCorrectPoints(pointAmount)
        }

    }

    private fun selectedCorrectPoints(pointAmount: Int?) {
        selectedCorrect()
        with(binding.starsTextView) {
            visible()
            text = "+".plus("$pointAmount")
        }
    }

    private fun selectedCorrect() {
        with(binding) {
            root.setColorStateList(com.space.common.util.C.success)
            optionTitleTextView.setTextColor(context.getColor(com.space.common.util.C.neutral_05_white))
            starsTextView.gone()
        }
    }

    private fun selectedIncorrect() {
        with(binding) {
            root.setColorStateList(com.space.common.util.C.wrong)
            optionTitleTextView.setTextColor(context.getColor(com.space.common.util.C.neutral_05_white))
            starsTextView.gone()
        }
    }

    private fun unselected() {
        with(binding) {
            root.setColorStateList(com.space.common.util.C.neutral_03_light_grey)
            optionTitleTextView.setTextColor(context.getColor(com.space.common.util.C.black))
            starsTextView.gone()
        }
    }


}
