package com.space.common.base.usecase

abstract class QuizBaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}
