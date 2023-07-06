package com.space.quiz_api

import com.space.common.model.question.domain.QuizQuestionDomainModel

interface UpdateQuestion {
    suspend operator fun invoke(questionDomainModel: QuizQuestionDomainModel)

}