package com.space.quizapp.domain.usecase.user

import com.space.common.util.QuizRegex
import com.space.common.util.QuizUserValidation
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class ValidateUserUseCase : QuizBaseUseCase<String, com.space.common.util.QuizUserValidation>() {
    override suspend fun invoke(params: String?): com.space.common.util.QuizUserValidation {
        return when {
            params!! == "admin" -> com.space.common.util.QuizUserValidation.VALID
            !params.contains(Regex(com.space.common.util.QuizRegex.USERNAME_PATTERN)) -> com.space.common.util.QuizUserValidation.INVALID_CONTAINING
            params.length < com.space.common.util.QuizUserValidation.MINIMUM_LENGTH -> com.space.common.util.QuizUserValidation.INVALID_LENGTH_SHORT
            params.length > com.space.common.util.QuizUserValidation.MAXIMUM_LENGTH -> com.space.common.util.QuizUserValidation.INVALID_LENGTH_LONG
            else -> com.space.common.util.QuizUserValidation.VALID
        }
    }
}