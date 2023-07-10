package com.space.main.data.remote.service

import com.space.common.util.QuizConstants.QUIZ_ENDPOINT
import com.space.main.data.remote.model.QuizSubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuizQuestionsApiService {

    @GET(QUIZ_ENDPOINT)
    suspend fun retrieveQuestions(): Response<List<QuizSubjectDto>>

}
