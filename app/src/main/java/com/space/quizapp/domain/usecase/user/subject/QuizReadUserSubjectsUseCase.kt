package com.space.quizapp.domain.usecase.user.subject

import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizReadUserSubjectsUseCase(
    private val readUserDataUC: QuizBaseUseCase<Unit, QuizUserDomainModel>,
    private val userSubjectRepository: QuizUserSubjectRepository,
    private val subjectRepository: QuizRepository
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
