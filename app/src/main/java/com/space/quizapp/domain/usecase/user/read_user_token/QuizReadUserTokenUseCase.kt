package com.space.quizapp.domain.usecase.user.read_user_token

import com.space.quizapp.domain.repository.user.QuizUserTokenRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import org.koin.java.KoinJavaComponent.inject


abstract class QuizReadUserTokenUseCase : QuizBaseUseCase<Unit, String>() {
    protected val repository by inject<QuizUserTokenRepository>(QuizUserTokenRepository::class.java)
}
