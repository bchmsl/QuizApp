package com.space.quizapp.common.util

import com.space.quizapp.common.resource.QuizResource
import retrofit2.Response

interface ApiHelper {
    suspend fun <DTO : Any> retrofitCall(
        call: suspend () -> Response<DTO>
    ): QuizResource<DTO> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    QuizResource.Success(response.body()!!)
                } else {
                    QuizResource.Error(QuizCustomThrowable(S.error_bad_request))
                }
            } else {
                QuizResource.Error(QuizCustomThrowable(response.message()))
            }
        } catch (e: Throwable) {
            QuizResource.Error(QuizCustomThrowable(e.message))
        }
    }
}
