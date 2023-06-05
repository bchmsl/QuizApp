package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizSubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET("9a2cbd2f-d77c-498a-a410-91351fe42577")
    suspend fun retrieveQuestions(): Response<List<QuizSubjectDto>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}
