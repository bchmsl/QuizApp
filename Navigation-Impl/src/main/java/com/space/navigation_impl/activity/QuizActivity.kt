package com.space.navigation_impl.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.navigation_impl.R
import com.space.navigation_impl.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private val binding by lazy { ActivityQuizBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
    }
}
