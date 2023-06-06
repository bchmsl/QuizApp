package com.space.quizapp.domain.usecase.base

import com.space.quizapp.data.repository.BaseRepository

abstract class QuizBaseUseCase<in Params, out T>() {
    abstract val repository: BaseRepository
    abstract suspend operator fun invoke(params: Params? = null): T
}
