package com.space.quizapp.common.util

import android.view.LayoutInflater
import android.view.ViewGroup
import com.space.quizapp.R

typealias S = R.string
typealias C = R.color
typealias D = R.drawable

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

