package com.space.common.extensions.utils

import android.widget.Button
import com.space.common.util.C

fun Button.enable(isEnabled: Boolean) {
    setEnabled(isEnabled)
    setColorStateList(if (isEnabled) C.yellow_primary else C.neutral_02_grey)
}
