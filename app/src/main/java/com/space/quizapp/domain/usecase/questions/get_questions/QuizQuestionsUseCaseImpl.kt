package com.space.quizapp.domain.usecase.questions.get_questions

import com.space.quizapp.common.extensions.utils.onError
import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.usecase.questions.base.QuizBaseQuestionsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class QuizQuestionsUseCaseImpl : QuizBaseQuestionsUseCase(), QuizQuestionsUseCase {
    override suspend fun invoke() = flow<QuizResource<List<QuizSubjectDomainModel>>> {
        repository.retrieveQuestions().collect {
            it.onSuccess {
                repository.
            }.onError {

            }
        }
    }
}
