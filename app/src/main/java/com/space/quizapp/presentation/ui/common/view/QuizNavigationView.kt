package com.space.quizapp.presentation.ui.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizapp.common.extensions.utils.invisible
import com.space.quizapp.common.extensions.utils.visible
import com.space.quizapp.databinding.QuizViewNavigationBinding

class QuizNavigationView(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {
    private val binding =
        QuizViewNavigationBinding.inflate(LayoutInflater.from(context), this, true)


    private var backButtonCallback: (() -> Unit)? = null
    fun onBackButtonPressed(block: () -> Unit) {
        backButtonCallback = block
    }

    private var closeButtonCallback: (() -> Unit)? = null
    fun onCloseButtonPressed(block: () -> Unit) {
        closeButtonCallback = block
    }


    fun setContent(title: String, closeAvailable: Boolean, backAvailable: Boolean) {
        binding.navTitleTextView.text = title
        setCloseAvailable(closeAvailable)
        setBackAvailable(backAvailable)
    }

    private fun setCloseAvailable(closeAvailable: Boolean) {
        with(binding.navExitImageButton) {
            if (closeAvailable) {
                visible()
            } else {
                invisible()
            }
            setOnClickListener {
                closeButtonCallback?.invoke()
            }

        }
    }

    private fun setBackAvailable(backAvailable: Boolean) {
        with(binding.navBackImageButton) {
            if (backAvailable) {
                visible()
            } else {
                invisible()
            }
            setOnClickListener {
                backButtonCallback?.invoke()
            }
        }
    }

}
