package com.space.main.domain.usecase.user.subject

import com.space.common.base.usecase.BaseUseCase
import com.space.main.domain.model.user.UserSubjectDomainModel
import com.space.main.domain.repository.quiz.SubjectsRepository
import com.space.main.domain.repository.user.UserSubjectRepository
import com.space.main.domain.usecase.user.ReadUserDataUseCase

class ReadUserSubjectsUseCase(
    private val readUserDataUC: ReadUserDataUseCase,
    private val userSubjectRepository: UserSubjectRepository,
    private val subjectRepository: SubjectsRepository
) : BaseUseCase<Unit, List<UserSubjectDomainModel>>() {
    override suspend fun invoke(params: Unit?): List<UserSubjectDomainModel> {
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
