package com.space.quizapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.space.quizapp.R
import com.space.quizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private val binding by lazy{ActivityQuizBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
    }
}
