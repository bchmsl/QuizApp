package com.space.quizapp.presentation.ui.start.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import com.space.quizapp.R
import com.space.quizapp.presentation.base.view.BaseCustomView

class CustomBlueVector(context: Context) : BaseCustomView(context) {

    private val centerX get() = width / 2
    private val radius get() = width / 2

    override fun startDrawing(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        drawCorner(canvas)
        drawVector(canvas)

    }

    private fun drawVector(canvas: Canvas) {
        val heightDiff = (width / 2) - (height / 3)

        val centerY1 = height / 3 + heightDiff
        val centerY2 = height * 2 / 3 - heightDiff

        path.apply {
            reset()
            paint.color = Color.parseColor("#5a84e8")
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
            canvas.drawPath(path, paint)
        }
    }

    private fun drawCorner(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.resources.getColor(R.color.blue_secondary_default, context.theme)
            moveTo(0f, 0f)
            lineTo(centerX, 0f)
            lineTo(0f, height / 2)
            close()
            canvas.drawPath(path, paint)
        }
    }
}
