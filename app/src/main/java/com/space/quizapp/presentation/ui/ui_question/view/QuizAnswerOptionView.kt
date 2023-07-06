package com.space.quizapp.presentation.ui.ui_question.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.common.extensions.utils.gone
import com.space.quizapp.common.extensions.utils.setColorStateList
import com.space.quizapp.common.extensions.utils.visible
import com.space.quizapp.common.util.C
import com.space.quizapp.databinding.QuizViewAnswerOptionBinding
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuizAnswerOptionView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {
    private val binding =
        QuizViewAnswerOptionBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setSelection(QuizAnswerSelectedState.ANSWER_NEUTRAL)
    }

    fun setContent(title: String) {
        binding.optionTitleTextView.text = title
    }

    fun setSelection(selectState: QuizAnswerSelectedState, pointAmount: Int? = null) {
        when (selectState) {
            QuizAnswerSelectedState.ANSWER_SELECTED_POSITIVE -> selectedCorrect()
            QuizAnswerSelectedState.ANSWER_SELECTED_NEGATIVE -> selectedIncorrect()
            QuizAnswerSelectedState.ANSWER_NEUTRAL -> unselected()
            QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT -> selectedCorrectPoints(pointAmount)
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
            root.setColorStateList(C.success)
            optionTitleTextView.setTextColor(context.getColor(C.neutral_05_white))
            starsTextView.gone()
        }
    }

    private fun selectedIncorrect() {
        with(binding) {
            root.setColorStateList(C.wrong)
            optionTitleTextView.setTextColor(context.getColor(C.neutral_05_white))
            starsTextView.gone()
        }
    }

    private fun unselected() {
        with(binding) {
            root.setColorStateList(C.neutral_03_light_grey)
            optionTitleTextView.setTextColor(context.getColor(C.black))
            starsTextView.gone()
        }
    }


}
