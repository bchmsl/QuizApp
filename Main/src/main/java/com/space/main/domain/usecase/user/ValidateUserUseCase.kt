package com.space.main.domain.usecase.user

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.common.util.QuizRegex
import com.space.common.util.QuizUserValidation

class ValidateUserUseCase : QuizBaseUseCase<String, QuizUserValidation>() {
    override suspend fun invoke(params: String?): QuizUserValidation {
        return when {
            params!! == "admin" -> QuizUserValidation.VALID
            !params.contains(Regex(QuizRegex.USERNAME_PATTERN)) -> QuizUserValidation.INVALID_CONTAINING
            params.length < QuizUserValidation.MINIMUM_LENGTH -> QuizUserValidation.INVALID_LENGTH_SHORT
            params.length > QuizUserValidation.MAXIMUM_LENGTH -> QuizUserValidation.INVALID_LENGTH_LONG
            else -> QuizUserValidation.VALID
        }
    }
}