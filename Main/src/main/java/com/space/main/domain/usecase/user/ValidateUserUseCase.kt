package com.space.main.domain.usecase.user

import com.space.common.base.usecase.BaseUseCase
import com.space.common.util.QuizRegex
import com.space.common.util.UserValidation

class ValidateUserUseCase : BaseUseCase<String, UserValidation>() {
    override suspend fun invoke(params: String?): UserValidation {
        return when {
            params!! == "admin" -> UserValidation.VALID
            !params.contains(Regex(QuizRegex.USERNAME_PATTERN)) -> UserValidation.INVALID_CONTAINING
            params.length < UserValidation.MINIMUM_LENGTH -> UserValidation.INVALID_LENGTH_SHORT
            params.length > UserValidation.MAXIMUM_LENGTH -> UserValidation.INVALID_LENGTH_LONG
            else -> UserValidation.VALID
        }
    }
}