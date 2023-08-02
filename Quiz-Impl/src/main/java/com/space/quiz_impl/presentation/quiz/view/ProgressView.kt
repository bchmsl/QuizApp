package com.space.quiz_impl.presentation.quiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizimpl.databinding.ViewProgressBinding

class ProgressView(
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
        setContent()
    }

    fun setProgress(index: Int) {
        currentValue = index
        setContent()
    }

    fun setPoints(pointsValue: Int) {
        correctAnswersValue = pointsValue
        setContent()
    }

    private fun setContent() {
        with(binding) {
            val progress = "$currentValue/$maxValue"
            progressTextView.text = progress
            currentScoreTextView.text =
                context.getString(com.space.common.util.S.current_score, correctAnswersValue)
            binding.progressIndicator.setProgress(currentValue, true)
        }
    }

    fun clear() {
        this.correctAnswersValue = 0
        this.currentValue = 0
        this.maxValue = 0
        setContent()
    }
}