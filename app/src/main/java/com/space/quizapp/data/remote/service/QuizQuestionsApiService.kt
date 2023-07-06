package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizSubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET(QUIZ_ENDPOINT)
    suspend fun retrieveQuestions(): Response<List<QuizSubjectDto>>

}
