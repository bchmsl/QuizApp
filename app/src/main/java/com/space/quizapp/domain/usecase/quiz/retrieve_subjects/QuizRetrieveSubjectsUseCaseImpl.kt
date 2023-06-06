package com.space.quizapp.domain.usecase.quiz.retrieve_subjects

import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel

class QuizRetrieveSubjectsUseCaseImpl : QuizRetrieveSubjectsUseCase() {
    override suspend fun invoke(params: Nothing?): List<QuizSubjectDomainModel> {
        return repository.getLocalSubjects()
    }
}
