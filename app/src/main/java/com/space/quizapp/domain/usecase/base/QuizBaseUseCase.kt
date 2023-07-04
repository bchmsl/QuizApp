package com.space.quizapp.domain.usecase.base

abstract class QuizBaseUseCase<in Params, out T> {
    abstract suspend operator fun invoke(params: Params? = null): T
}
