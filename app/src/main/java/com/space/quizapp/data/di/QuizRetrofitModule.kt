package com.space.quizapp.data.di

import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.data.remote.service.QuizQuestionsApiService.Companion.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(QuizQuestionsApiService::class.java)
    }
}
