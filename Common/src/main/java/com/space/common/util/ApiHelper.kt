package com.space.common.util

import com.space.common.resource.Resource
import retrofit2.Response

interface ApiHelper {
    suspend fun <DTO : Any> retrofitCall(
        call: suspend () -> Response<DTO>
    ): Resource<DTO> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error(CustomThrowable(response.errorBody().toString()))
                }
            } else {
                Resource.Error(CustomThrowable(response.message()))
            }
        } catch (e: Throwable) {
            Resource.Error(CustomThrowable(e.message))
        }
    }
}
