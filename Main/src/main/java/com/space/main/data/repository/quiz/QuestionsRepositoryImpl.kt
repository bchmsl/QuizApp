package com.space.main.data.repository.quiz

import com.space.common.model.question.data.QuestionEntityMapper
import com.space.common.model.question.domain.QuestionDomainModel
import com.space.main.data.local.database.dao.QuestionsDao
import com.space.main.domain.repository.quiz.QuestionsRepository

class QuestionsRepositoryImpl(
    private val questionsDao: QuestionsDao,
    private val questionEntityMapper: QuestionEntityMapper
) : QuestionsRepository() {
    override suspend fun getNextQuestion(subjectTitle: String): QuestionDomainModel? {
        return questionsDao.getNextQuestionBySubjectTitle(subjectTitle)
            ?.let { questionEntityMapper.toDomain(it) }
    }

    override suspend fun updateQuestion(question: QuestionDomainModel) {
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

    override suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<QuestionDomainModel> {
        return questionsDao.getAllQuestions(subjectTitle).map { questionEntityMapper.toDomain(it) }
    }
}
