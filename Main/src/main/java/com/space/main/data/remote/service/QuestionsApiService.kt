package com.space.main.data.remote.service

import com.space.common.util.Constants.QUIZ_ENDPOINT
import com.space.main.data.remote.model.SubjectDto
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsApiService {

    @GET(QUIZ_ENDPOINT)
    suspend fun retrieveQuestions(): Response<List<SubjectDto>>

}
