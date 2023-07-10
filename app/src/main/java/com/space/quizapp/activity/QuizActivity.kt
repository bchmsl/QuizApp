package com.space.quizapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.space.quizapp.databinding.ActivityQuizBinding
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class QuizActivity : AppCompatActivity() {
    private val binding by lazy { ActivityQuizBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val activityModule = module {
            single { findNavController(binding.navHostFragment.id) }
        }
        loadKoinModules(activityModule)
    }
}
