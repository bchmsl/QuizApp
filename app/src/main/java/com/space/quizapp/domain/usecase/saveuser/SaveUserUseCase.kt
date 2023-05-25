package com.space.quizapp.domain.usecase.saveuser

import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface SaveUserUseCase {
    suspend operator fun invoke(userDomainModel: UserDomainModel): Flow<ValidateUser?>
}
