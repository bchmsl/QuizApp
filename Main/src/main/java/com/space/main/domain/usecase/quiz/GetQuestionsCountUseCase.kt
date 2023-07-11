package com.space.main.domain.usecase.quiz

import com.space.main.domain.repository.quiz.SubjectsRepository
import com.space.quiz_api.GetQuestionsCount

class GetQuestionsCountUseCase(private val quizRepository: SubjectsRepository) :
    GetQuestionsCount {
    override suspend fun invoke(subjectTitle: String): Int {
        return quizRepository.retrieveLocalSubjectByTitle(subjectTitle).questionsCount
    }
}
