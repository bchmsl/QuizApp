package com.space.quizapp.domain.usecase.questions.get_questions

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizQuestionsUseCaseImpl : QuizQuestionsUseCase() {
    override suspend fun invoke(params: Nothing?): QuizResource<List<QuizSubjectDomainModel>> {
        return repository.retrieveQuestions()
    }
}
