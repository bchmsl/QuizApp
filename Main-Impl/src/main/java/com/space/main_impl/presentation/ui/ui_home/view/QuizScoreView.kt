package com.space.main_impl.presentation.ui.ui_home.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.common.extensions.utils.roundToSingleDecimal
import com.space.main_impl.databinding.QuizViewScoreSectionBinding

class QuizScoreView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {
    private val binding =
        QuizViewScoreSectionBinding.inflate(LayoutInflater.from(context), this, true)

    fun setGpa(gpa: Float) {
        binding.gpaTextView.text = gpa.roundToSingleDecimal().toString()
    }

    fun setOnClickListener(block: () -> Unit) {
        binding.root.setOnClickListener {
            block.invoke()
        }
    }
}
