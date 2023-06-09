package com.space.quizapp.data.repository.quiz

import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.common.util.ApiHelper
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionEntityDomainMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.subject.QuizSubjectDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.subject.QuizSubjectEntityDomainMapper
import com.space.quizapp.data.remote.model.mapper.question.QuizQuestionDtoDomainMapper
import com.space.quizapp.data.remote.model.mapper.subject.QuizSubjectDtoDomainMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository

class QuizRepositoryImpl(
    private val api: QuizQuestionsApiService,
    private val dao: QuizSubjectsDao,
    private val quizSubjectDtoDomainMapper: QuizSubjectDtoDomainMapper,
    private val quizQuestionDtoDomainMapper: QuizQuestionDtoDomainMapper,
    private val quizSubjectDomainEntityMapper: QuizSubjectDomainEntityMapper,
    private val quizQuestionDomainEntityMapper: QuizQuestionDomainEntityMapper,
    private val quizSubjectEntityDomainMapper: QuizSubjectEntityDomainMapper,
    private val quizQuestionEntityDomainMapper: QuizQuestionEntityDomainMapper
) : QuizRepository(), ApiHelper {


    override suspend fun retrieveSubjects(): List<QuizSubjectDomainModel> {
        retrofitCall { api.retrieveQuestions() }
            .onSuccess { data ->
                dao.insertSubjects(data
                    .map { quizSubjectDtoDomainMapper(it) }
                    .map { quizSubjectDomainEntityMapper(it) }
                )
                data.forEach { subject ->
                    dao.insertQuestions(subject.questions
                        .map { quizQuestionDtoDomainMapper(it) }
                        .map { quizQuestionDomainEntityMapper(it) }
                    )
                }
            }
        return getLocalSubjects()
    }

    override suspend fun getLocalSubjects(): List<QuizSubjectDomainModel> {
        return dao.retrieveSubjects().map { quizSubjectEntityDomainMapper(it) }
    }

    override suspend fun getLocalQuestionsBySubject(
        subjectId: Int
    ): List<QuizQuestionDomainModel> {
        return dao.retrieveQuestionsBySubjectId(subjectId)
            .map { quizQuestionEntityDomainMapper(it) }
    }
}
