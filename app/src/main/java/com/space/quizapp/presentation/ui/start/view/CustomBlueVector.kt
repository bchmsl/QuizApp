package com.space.quizapp.presentation.ui.start.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.space.quizapp.R

class CustomBlueVector(
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

    private val centerX get() = width / 2
    private val radius get() = width / 2

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCorner(canvas)
        drawVector(canvas)

    }

    private fun drawVector(canvas: Canvas) {
        val heightDiff = (width / 2) - (height / 3)

        val centerY1 = height / 3 + heightDiff
        val centerY2 = height * 2 / 3 - heightDiff

        path.apply {
            reset()
            fillPaint.color = Color.parseColor("#5a84e8")
            addCircle(centerX, centerY1, radius, Path.Direction.CW)
            addCircle(centerX, centerY2, radius, Path.Direction.CW)

            moveTo(centerX, 0f)
            lineTo(width, 0f)
            lineTo(width, centerY2)
            lineTo(centerX, height)
            lineTo(0f, height)
            lineTo(0f, centerY1)
            lineTo(centerX, 0f)
            close()
            canvas.drawPath(path, fillPaint)
        }
    }

    private fun drawCorner(canvas: Canvas) {
        path.apply {
            reset()
            fillPaint.color = context.resources.getColor(R.color.blue_secondary_default, context.theme)
            moveTo(0f, 0f)
            lineTo(centerX, 0f)
            lineTo(0f, height / 2)
            close()
            canvas.drawPath(path, fillPaint)
        }
    }
}
