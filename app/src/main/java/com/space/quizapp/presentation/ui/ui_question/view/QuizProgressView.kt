package com.space.quizapp.presentation.ui.ui_question.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.databinding.ViewProgressBinding

class QuizProgressView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {
    private val binding =
        ViewProgressBinding.inflate(LayoutInflater.from(context), this, true)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}