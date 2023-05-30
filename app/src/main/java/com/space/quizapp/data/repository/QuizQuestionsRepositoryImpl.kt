package com.space.quizapp.data.repository

import com.space.quizapp.common.resource.Resource
import com.space.quizapp.data.remote.model.QuizQuestionsDtoDomainMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.QuizQuestionsDomainModel
import com.space.quizapp.domain.repository.QuizQuestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizQuestionsRepositoryImpl(
    private val api: QuizQuestionsApiService,
    private val quizQuestionsDtoDomainMapper: QuizQuestionsDtoDomainMapper
) : QuizQuestionsRepository {
    override suspend fun retrieveQuestions(): Flow<Resource<List<QuizQuestionsDomainModel>>> =
        flow {
            try {
                val response = api.retrieveQuestions()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        Resource.Success(
                            (response.body()!!.map { quizQuestionsDtoDomainMapper(it) })
                        )
                    )
                } else {
                    emit(Resource.Error(Throwable("Invalid response. Try again!")))
                }
            } catch (e: Throwable) {
                emit(Resource.Error(e))
            }
        }
}
