package com.space.quizapp.presentation.ui.question.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.space.quizapp.R
import com.space.quizapp.presentation.base.view.BaseCustomView

class CustomGreyVector(context: Context) : BaseCustomView(context) {

    override fun startDrawing(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        drawVector(canvas)
    }

    private fun drawVector(canvas: Canvas) {
        val centerX1 = width / 4
        val centerX2 = width * 3 / 4
        val radius = width / 4
        val centerY = height - radius

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_light)
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
        canvas.drawPath(path, paint)
    }
}
