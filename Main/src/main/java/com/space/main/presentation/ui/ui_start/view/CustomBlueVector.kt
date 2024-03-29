package com.space.main.presentation.ui.ui_start.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import com.space.common.base.view.BaseCustomView
import com.space.common.util.C

class CustomBlueVector(context: Context, attributeSet: AttributeSet) :
    BaseCustomView(context, attributeSet) {

    private val radius get() = width

    override fun startDrawing(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        drawCorner(canvas)
        drawVector(canvas)

    }

    private fun drawVector(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.getColor(C.blue_secondary_light)

            val topRectF = RectF(0f, 0f, radius, radius)
            val bottomRectF = RectF(width - radius, height - radius, width, height)

            moveTo(radius, 0f)
            lineTo(width, 0f)
            lineTo(width, height - radius)
            arcTo(bottomRectF, 0f, 90f)
            lineTo(0f, height)
            lineTo(0f, radius)
            arcTo(topRectF, 180f, 90f)
            close()
            canvas.drawPath(path, paint)
        }
    }

    private fun drawCorner(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.getColor(C.blue_secondary_default)
            moveTo(0f, height)
            lineTo(0f, 0f)
            lineTo(width, 0f)
            close()
            canvas.drawPath(path, paint)
        }
    }
}
