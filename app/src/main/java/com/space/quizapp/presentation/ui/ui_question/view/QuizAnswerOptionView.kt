package com.space.quizapp.presentation.ui.ui_question.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.space.quizapp.common.util.C
import com.space.quizapp.databinding.QuizViewAnswerOptionBinding
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState

class QuizAnswerOptionView(
    context: Context,
    attributeSet: AttributeSet,
) : FrameLayout(context, attributeSet) {
    private val binding =
        QuizViewAnswerOptionBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setSelection(QuizAnswerSelectedState.ANSWER_UNSELECTED)
    }

    fun setContent(title: String) {
        binding.optionTitleTextView.text = title
    }

    fun setSelection(selectState: QuizAnswerSelectedState) {
        when (selectState) {
            QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT -> selectedCorrect()
            QuizAnswerSelectedState.ANSWER_SELECTED_INCORRECT -> selectedIncorrect()
            QuizAnswerSelectedState.ANSWER_UNSELECTED -> unselected()
        }

    }

    private fun selectedCorrect() {
        with(binding) {
            root.backgroundTintList = ColorStateList.valueOf(context.getColor(C.success))
            optionTitleTextView.setTextColor(context.getColor(C.neutral_05_white))
            starsTextView.visibility = View.VISIBLE
            starsTextView.text = "+1"
        }
    }

    private fun selectedIncorrect() {
        with(binding) {
            root.backgroundTintList = ColorStateList.valueOf(context.getColor(C.wrong))
            optionTitleTextView.setTextColor(context.getColor(C.neutral_05_white))
            starsTextView.visibility = View.GONE
        }
    }

    private fun unselected() {
        with(binding) {
            root.backgroundTintList =
                ColorStateList.valueOf(context.getColor(C.neutral_03_light_grey))
            optionTitleTextView.setTextColor(context.getColor(C.black))
            starsTextView.visibility = View.GONE
        }
    }
}
