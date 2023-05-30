package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizQuestionsDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET("73a800bf-fb55-4aae-8db7-12841a65579e")
    suspend fun retrieveQuestions(): Response<List<QuizQuestionsDto>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}
