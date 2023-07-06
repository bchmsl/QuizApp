package com.space.quiz_api

import com.space.common.model.question.domain.QuizQuestionDomainModel

interface GetNextQuestion {
    suspend operator fun invoke(subjectTitle: String): QuizQuestionDomainModel?
}