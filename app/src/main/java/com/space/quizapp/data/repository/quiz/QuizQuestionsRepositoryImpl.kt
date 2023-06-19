package com.space.quizapp.data.repository.quiz

import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizQuestionEntityMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel

class QuizQuestionsRepositoryImpl(
    private val questionsDao: QuizQuestionsDao,
    private val questionEntityMapper: QuizQuestionEntityMapper
) {
    suspend fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel? {
        return questionsDao.getNextQuestionBySubjectTitle(subjectTitle)
            ?.let { questionEntityMapper.toDomain(it) }
    }

    suspend fun updateQuestion(question: QuizQuestionDomainModel) {
        questionsDao.updateQuestion(questionEntityMapper.toEntity(question))
    }
}
