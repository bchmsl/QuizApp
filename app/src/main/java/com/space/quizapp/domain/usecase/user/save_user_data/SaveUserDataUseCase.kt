package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.ValidateUser
import com.space.quizapp.domain.model.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface SaveUserDataUseCase {
    suspend operator fun invoke(userDomainModel: UserDomainModel): Flow<ValidateUser>
}
