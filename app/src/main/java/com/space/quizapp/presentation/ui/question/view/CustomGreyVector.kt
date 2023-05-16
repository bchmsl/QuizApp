package com.space.quizapp.presentation.ui.question.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.space.quizapp.R

class CustomGreyVector(
    context: Context,
    attrs: AttributeSet
) : View(context, attrs) {
    private val fillPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
        }
    }
    private val path by lazy { Path() }

    private val width get() = getWidth().toFloat()
    private val height get() = getHeight().toFloat()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawVector(canvas)
    }

    private fun drawVector(canvas: Canvas) {

        val centerX1 = width / 4
        val centerX2 = width * 3 / 4
        val radius = width / 4
        val centerY = height - radius

        path.apply {
            reset()
            fillPaint.color = context.getColor(R.color.blue_secondary_light)
            addCircle(centerX1, centerY, radius, Path.Direction.CW)
            addCircle(centerX2, centerY, radius, Path.Direction.CW)

            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, centerY)
            lineTo(centerX2, height)

            lineTo(centerX1, height)
            lineTo(0f, centerY)
            lineTo(0f, 0f)
            close()
        }
        canvas.drawPath(path, fillPaint)
    }
}
