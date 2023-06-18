package com.space.quizapp.domain.usecase.user.subject

import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.user.QuizUserSubjectRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizSaveUserSubjectUseCase(
    private val repository: QuizUserSubjectRepository,
    private val readUserData: QuizBaseUseCase<Unit, QuizUserDomainModel>
) : QuizBaseUseCase<QuizUserSubjectDomainModel, Unit>() {
    override suspend fun invoke(params: QuizUserSubjectDomainModel?) {
        val username = readUserData()
        val model = params!!.copy(username = username.username)
        repository.insertUserSubject(model)
    }
}
