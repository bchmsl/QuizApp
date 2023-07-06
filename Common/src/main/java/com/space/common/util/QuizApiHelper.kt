package com.space.common.util

import com.space.common.resource.QuizResource
import retrofit2.Response

interface QuizApiHelper {
    suspend fun <DTO : Any> retrofitCall(
        call: suspend () -> Response<DTO>
    ): QuizResource<DTO> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    QuizResource.Success(response.body()!!)
                } else {
                    QuizResource.Error(QuizCustomThrowable(response.errorBody().toString()))
                }
            } else {
                QuizResource.Error(QuizCustomThrowable(response.message()))
            }
        } catch (e: Throwable) {
            QuizResource.Error(QuizCustomThrowable(e.message))
        }
    }
}
