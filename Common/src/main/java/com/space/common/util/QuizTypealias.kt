package com.space.common.util

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.corecommon.R

typealias S = R.string
typealias C = R.color

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

