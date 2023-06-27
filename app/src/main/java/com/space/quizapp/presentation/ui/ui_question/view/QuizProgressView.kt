package com.space.quizapp.presentation.ui.ui_question.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.common.util.S
import com.space.quizapp.databinding.ViewProgressBinding

class QuizProgressView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val binding =
        ViewProgressBinding.inflate(LayoutInflater.from(context), this, true)

    private var maxValue = 0
    private var currentValue = 0
    private var correctAnswersValue = 0

    fun setMaxValue(maxValue: Int) {
        this.maxValue = maxValue
        binding.progressIndicator.max = maxValue
    }

    fun setProgress(addOne: Boolean, correctAnswersValue: Int? = null) {
        if (addOne) this.currentValue += 1
        correctAnswersValue?.let {
            this.correctAnswersValue = it
        }
        setContent()
    }

    private fun setContent() {
        with(binding) {
            progressTextView.text = "$currentValue/$maxValue"
            currentScoreTextView.text = context.getString(S.current_score, correctAnswersValue)
            progressIndicator.progress = currentValue
        }
    }

    fun clear() {
        this.correctAnswersValue = 0
        this.currentValue = 0
        this.maxValue = 0
        setContent()
    }
}