package com.space.quizapp.data.repository.quiz

import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizQuestionEntityMapper
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository

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
}
