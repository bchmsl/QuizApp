package com.space.common.extensions.utils

fun Float.roundToSingleDecimal(): Float {
    return String.format("%.1f", this).toFloat()
}
