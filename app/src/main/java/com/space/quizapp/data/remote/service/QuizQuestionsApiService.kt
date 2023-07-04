package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizSubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET("8ade4e0b-bee1-4eae-a98b-47edeea68324")
    suspend fun retrieveQuestions(): Response<List<QuizSubjectDto>>

}
