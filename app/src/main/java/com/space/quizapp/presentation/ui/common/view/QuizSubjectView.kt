package com.space.quizapp.presentation.ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
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
            startButton.setCompoundDrawables(
                null,
                null,
                AppCompatResources.getDrawable(context, D.quiz_ic_next),
                null
            )
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
