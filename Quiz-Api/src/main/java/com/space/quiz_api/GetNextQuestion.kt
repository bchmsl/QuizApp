package com.space.quiz_api

import com.space.common.model.question.domain.QuestionDomainModel

interface GetNextQuestion {
    suspend operator fun invoke(subjectTitle: String): QuestionDomainModel?
}