package com.space.quizapp.data.repository.quiz

import com.space.common.model.question.data.QuizQuestionEntityMapper
import com.space.common.model.question.domain.QuizQuestionDomainModel
import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository

class QuizQuestionsRepositoryImpl(
    private val questionsDao: QuizQuestionsDao,
    private val questionEntityMapper: com.space.common.model.question.data.QuizQuestionEntityMapper
) : QuizQuestionsRepository() {
    override suspend fun getNextQuestion(subjectTitle: String): com.space.common.model.question.domain.QuizQuestionDomainModel? {
        return questionsDao.getNextQuestionBySubjectTitle(subjectTitle)
            ?.let { questionEntityMapper.toDomain(it) }
    }

    override suspend fun updateQuestion(question: com.space.common.model.question.domain.QuizQuestionDomainModel) {
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

    override suspend fun getQuestionsBySubjectTitle(subjectTitle: String): List<com.space.common.model.question.domain.QuizQuestionDomainModel> {
        return questionsDao.getAllQuestions(subjectTitle).map { questionEntityMapper.toDomain(it) }
    }
}
