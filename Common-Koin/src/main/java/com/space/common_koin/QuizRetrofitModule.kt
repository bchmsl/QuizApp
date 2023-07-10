package com.space.common_koin

import com.space.common.util.QuizConstants.BASE_URL
import com.space.main.data.remote.service.QuizQuestionsApiService
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
