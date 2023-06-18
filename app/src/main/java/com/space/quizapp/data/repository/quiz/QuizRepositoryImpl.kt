package com.space.quizapp.data.repository.quiz

import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.common.util.QuizApiHelper
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizQuestionEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizSubjectEntityMapper
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoMapper
import com.space.quizapp.data.remote.model.mapper.QuizSubjectDtoMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository

class QuizRepositoryImpl(
    private val questionsApi: QuizQuestionsApiService,
    private val subjectsDao: QuizSubjectsDao,
    private val subjectDtoMapper: QuizSubjectDtoMapper,
    private val questionDtoMapper: QuizQuestionDtoMapper,
    private val subjectEntityMapper: QuizSubjectEntityMapper,
    private val questionEntityMapper: QuizQuestionEntityMapper
) : QuizRepository(), QuizApiHelper {

    override suspend fun retrieveSubjects(): List<QuizSubjectDomainModel> {
        retrofitCall { questionsApi.retrieveQuestions() }
            .onSuccess { data ->
                subjectsDao.insertSubjects(data
                    .map { subjectDtoMapper.toDomain(it) }
                    .map { subjectEntityMapper.toEntity(it) }
                )
                data.forEach { subject ->
                    subjectsDao.insertQuestions(subject.questions
                        .map { questionDtoMapper.toDomain(it) }
                        .map { questionEntityMapper.toEntity(it) }
                    )
                }
            }
        return retrieveLocalSubjects()
    }

    override suspend fun retrieveLocalSubjects(): List<QuizSubjectDomainModel> {
        return subjectsDao.getSubjects().map { subjectEntityMapper.toDomain(it) }
    }

    override suspend fun getLocalQuestionsBySubjectId(
        subjectId: Int
    ): List<QuizQuestionDomainModel> {
        return subjectsDao.retrieveQuestionsBySubjectId(subjectId)
            .map { questionEntityMapper.toDomain(it) }
    }

    override suspend fun retrieveLocalSubjectByTitle(quizTitle: String): QuizSubjectDomainModel {
        return subjectEntityMapper.toDomain(subjectsDao.getSubjectByTitle(quizTitle))
    }
}
