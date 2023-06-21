package com.space.quizapp.domain.usecase.user.save_user_data

import com.space.quizapp.common.util.QuizValidateUser
import com.space.quizapp.domain.model.QuizUserDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizSaveUserDataUseCase {
    suspend operator fun invoke(userDomainModel: QuizUserDomainModel): Flow<QuizValidateUser>
}
