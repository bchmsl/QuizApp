package com.space.main.domain.usecase.user.subject

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main.domain.model.user.QuizUserSubjectDomainModel
import com.space.main.domain.repository.quiz.QuizSubjectsRepository
import com.space.main.domain.repository.user.QuizUserSubjectRepository
import com.space.main.domain.usecase.user.QuizReadUserDataUseCase

class QuizReadUserSubjectsUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository,
    private val subjectRepository: QuizSubjectsRepository
) : QuizBaseUseCase<Unit, List<QuizUserSubjectDomainModel>>() {
    override suspend fun invoke(params: Unit?): List<QuizUserSubjectDomainModel> {
        val username = readUserDataUC().username
        val userSubjects = userSubjectRepository.retrieveUserSubjects(username)

        userSubjects.forEach {
            val subject = subjectRepository.retrieveLocalSubjectByTitle(it.quizTitle)
            it.quizIcon = subject.quizIcon
            it.quizDescription = subject.quizDescription
        }
        return userSubjects
    }
}
