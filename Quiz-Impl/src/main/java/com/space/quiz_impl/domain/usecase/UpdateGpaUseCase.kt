package com.space.quiz_impl.domain.usecase

import com.space.common.base.usecase.BaseUseCase
import com.space.quiz_api.UpdateUserGpa

class UpdateGpaUseCase(
    private val updateUserGpa: UpdateUserGpa
) : BaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        updateUserGpa()
    }
}
