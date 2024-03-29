package com.space.quiz_impl.presentation.quiz.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import com.space.common.util.C

class CustomGreyVector(context: Context, attributeSet: AttributeSet) :
    com.space.common.base.view.BaseCustomView(context, attributeSet) {

    override fun startDrawing(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        drawVector(canvas)
    }

    private fun drawVector(canvas: Canvas) {
        val radius = width / 2
        path.apply {
            reset()
            paint.color = context.getColor(C.blue_secondary_lightest)
            val rightRectF = RectF(width - radius, height - radius, width, height)
            val leftRectF = RectF(0f, height - radius, radius, height)
            moveTo(0f, 0f)
            lineTo(width, 0f)
            arcTo(rightRectF, 0f, 90f)
            arcTo(leftRectF, 90f, 90f)
            close()
        }
        canvas.drawPath(path, paint)
    }
}
