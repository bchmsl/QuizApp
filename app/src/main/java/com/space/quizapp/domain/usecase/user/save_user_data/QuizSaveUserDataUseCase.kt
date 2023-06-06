package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.domain.model.user.QuizUserDomainModel
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class QuizSaveUserDataUseCase : QuizBaseUseCase<QuizUserDomainModel, QuizValidateUser>() {
    override val repository by inject<QuizUserDataRepository>(QuizUserDataRepository::class.java)
}
