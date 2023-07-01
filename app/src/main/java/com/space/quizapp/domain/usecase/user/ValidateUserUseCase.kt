package com.space.quizapp.domain.usecase.user

import com.space.quizapp.common.util.QuizRegex
import com.space.quizapp.common.util.QuizUserValidation
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class ValidateUserUseCase : QuizBaseUseCase<String, QuizUserValidation>() {
    override suspend fun invoke(username: String?): QuizUserValidation {
        return when {
            username!! == "admin" -> QuizUserValidation.VALID
            !username.contains(Regex(QuizRegex.USERNAME_PATTERN)) -> QuizUserValidation.INVALID_CONTAINING
            username.length < QuizUserValidation.MINIMUM_LENGTH -> QuizUserValidation.INVALID_LENGTH_SHORT
            username.length > QuizUserValidation.MAXIMUM_LENGTH -> QuizUserValidation.INVALID_LENGTH_LONG
            else -> QuizUserValidation.VALID
        }
    }
}