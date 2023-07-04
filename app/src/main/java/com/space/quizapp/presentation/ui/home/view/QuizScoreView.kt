package com.space.quizapp.presentation.ui.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.databinding.QuizViewScoreSectionBinding

class QuizScoreView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {
    private val binding =
        QuizViewScoreSectionBinding.inflate(LayoutInflater.from(context), this, true)

    fun setContent(gpa: Float) {
        with(binding) {
            gpaTextView.text = gpa.toString()
        }
    }

    fun setOnClickListener(block: () -> Unit) {
        binding.gpaDetailsTextView.setOnClickListener {
            block.invoke()
        }
    }
}