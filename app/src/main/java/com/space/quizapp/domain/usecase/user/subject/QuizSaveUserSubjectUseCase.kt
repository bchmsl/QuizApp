package com.space.quizapp.domain.usecase.user.subject

import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.user.read_user_data.QuizReadUserDataUseCase

class QuizSaveUserSubjectUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val userSubjectRepository: QuizUserSubjectRepository
) : QuizBaseUseCase<QuizUserSubjectDomainModel, Unit>() {
    override suspend fun invoke(params: QuizUserSubjectDomainModel?) {
        val username = readUserDataUC().username
        val model = params!!.copy(username = username)
        userSubjectRepository.insertUserSubject(model)
    }
}
