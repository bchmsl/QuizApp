package com.space.main_impl.data.repository.quiz

import com.space.common.model.question.data.QuizQuestionEntityMapper
import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.main_impl.data.local.database.dao.QuizQuestionsDao
import com.space.main_impl.domain.repository.quiz.QuizQuestionsRepository

class QuizQuestionsRepositoryImpl(
    private val questionsDao: QuizQuestionsDao,
    private val questionEntityMapper: QuizQuestionEntityMapper
) : QuizQuestionsRepository() {
    override suspend fun getNextQuestion(subjectTitle: String): QuizQuestionDomainModel? {
        return questionsDao.getNextQuestionBySubjectTitle(subjectTitle)
            ?.let { questionEntityMapper.toDomain(it) }
    }

    override suspend fun updateQuestion(question: QuizQuestionDomainModel) {
        questionsDao.updateQuestion(questionEntityMapper.toEntity(question))
    }

    override suspend fun resetAnsweredStates(subjectTitle: String) {
        questionsDao.getAllQuestions(subjectTitle).forEach {
            questionsDao.updateQuestion(it.copy(isAnswered = false))
        }
    }

    override suspend fun getQuestionsCount(subjectTitle: String): Int {
        return questionsDao.countQuestions(subjectTitle)
    }

    override suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<QuizQuestionDomainModel> {
        return questionsDao.getAllQuestions(subjectTitle).map { questionEntityMapper.toDomain(it) }
    }
}
