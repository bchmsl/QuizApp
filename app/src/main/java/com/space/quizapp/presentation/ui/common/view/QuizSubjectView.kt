package com.space.quizapp.presentation.ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.common.extensions.setImage
import com.space.quizapp.common.util.D
import com.space.quizapp.databinding.QuizViewSubjectBinding

class QuizSubjectView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {
    private val binding = QuizViewSubjectBinding.inflate(LayoutInflater.from(context), this, true)

    fun setPointsCount(points: Int? = null) {
        with(binding) {
            points?.let { points ->
                startButton.setCompoundDrawables(null, null, null, null)
                startButton.text = points.toString()
                return
            }
            startButton.setIconResource(D.quiz_ic_next)
        }
    }

    fun setContent(title: String, description: String, drawable: String) {
        with(binding) {
            subjectTitleTextView.text = title
            subjectDescriptionTextView.text = description
            iconSubjectImageView.setImage(drawable)
        }
    }
}
