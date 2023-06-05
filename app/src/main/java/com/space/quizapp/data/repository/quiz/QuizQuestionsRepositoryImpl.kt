package com.space.quizapp.data.repository.quiz

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.S
import com.space.quizapp.data.remote.model.mapper.QuizQuestionsDtoDomainMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuizQuestionsRepositoryImpl(
    private val api: QuizQuestionsApiService,
    private val quizQuestionsDtoDomainMapper: QuizQuestionsDtoDomainMapper
) : QuizQuestionsRepository {
    override suspend fun retrieveQuestions(): Flow<QuizResource<List<QuizSubjectDomainModel>>> =
        flow {
            try {
                val response = api.retrieveQuestions()
                if (response.isSuccessful && response.body() != null) {
                    emit(
                        QuizResource.Success(
                            (response.body()!!.map { quizQuestionsDtoDomainMapper(it) })
                        )
                    )
                } else {
                    emit(QuizResource.Error(QuizCustomThrowable(S.error_bad_request)))
                }
            } catch (e: Throwable) {
                emit(QuizResource.Error(QuizCustomThrowable(e.message)))
            }
        }
}
