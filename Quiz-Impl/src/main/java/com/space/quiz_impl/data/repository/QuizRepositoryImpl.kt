package com.space.quiz_impl.data.repository

import com.space.quiz_api.QuestionsApi
import com.space.quiz_impl.data.model.QuizQuestionEntityMapper
import com.space.quiz_impl.domain.model.QuizQuestionDomainModel
import com.space.quiz_impl.domain.repository.QuizRepository

class QuizRepositoryImpl(
    private val questionsApi: QuestionsApi,
    private val questionEntityMapper: QuizQuestionEntityMapper
): QuizRepository {
    override fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel? {
        return questionsApi.getNextQuestion(subjectTitle)?.let { questionEntityMapper.toDomain(it) }
    }

    override fun getQuestionsCount(subjectTitle: String): Int {
        return questionsApi.getQuestionsCount(subjectTitle)
    }

    override fun updateQuestion(questionDomainModel: QuizQuestionDomainModel) {
        questionsApi.updateQuestion(questionEntityMapper.toEntity(questionDomainModel))
    }
}