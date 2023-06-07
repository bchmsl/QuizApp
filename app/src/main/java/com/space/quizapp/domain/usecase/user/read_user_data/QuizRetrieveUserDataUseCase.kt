package com.space.quizapp.domain.usecase.user.read_user_data

import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class QuizRetrieveUserDataUseCase : QuizBaseUseCase<Unit, QuizUserDomainModel>() {
    protected val repository by inject<QuizUserDataRepository>(QuizUserDataRepository::class.java)
}
