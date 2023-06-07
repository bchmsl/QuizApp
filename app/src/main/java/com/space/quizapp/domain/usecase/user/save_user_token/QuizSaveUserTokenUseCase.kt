package com.space.quizapp.domain.usecase.user.save_user_token

import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject

abstract class QuizSaveUserTokenUseCase : QuizBaseUseCase<String, Unit>() {
    protected val repository by inject<QuizUserTokenRepository>(QuizUserTokenRepository::class.java)
}
