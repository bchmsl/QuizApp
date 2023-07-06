package com.space.main_impl.domain.usecase.quiz

import com.space.main_impl.domain.repository.quiz.QuizSubjectsRepository
import com.space.quiz_api.GetQuestionsCount

class GetQuestionsCountUseCase(private val quizRepository: QuizSubjectsRepository) :
    GetQuestionsCount {
    override suspend fun invoke(subjectTitle: String): Int {
        return quizRepository.retrieveLocalSubjectByTitle(subjectTitle).questionsCount
    }
}
