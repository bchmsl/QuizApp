package com.space.quiz_api

import com.space.common.model.question.domain.QuestionDomainModel

interface UpdateQuestion {
    suspend operator fun invoke(questionDomainModel: QuestionDomainModel)

}