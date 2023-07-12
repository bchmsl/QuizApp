package com.space.main.domain.usecase.questions

import com.space.common.base.usecase.BaseUseCase
import com.space.main.domain.repository.quiz.QuestionsRepository

class ResetQuestionAnsweredStatesUseCase(
    private val questionsRepository: QuestionsRepository
) : BaseUseCase<String, Unit>() {

    override suspend fun invoke(params: String?) {
        questionsRepository.resetAnsweredStates(params!!)
    }
}
