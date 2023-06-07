package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizSubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET("e7d3bc76-6574-4afd-a294-729fe9d56ed5")
    suspend fun retrieveQuestions(): Response<List<QuizSubjectDto>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}
