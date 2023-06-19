package com.space.quizapp.data.repository.quiz

import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.common.util.QuizApiHelper
import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizQuestionEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.QuizSubjectEntityMapper
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoMapper
import com.space.quizapp.data.remote.model.mapper.QuizSubjectDtoMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository

class QuizSubjectsRepositoryImpl(
    private val questionsApi: QuizQuestionsApiService,
    private val subjectsDao: QuizSubjectsDao,
    private val questionsDao: QuizQuestionsDao,
    private val subjectDtoMapper: QuizSubjectDtoMapper,
    private val questionDtoMapper: QuizQuestionDtoMapper,
    private val subjectEntityMapper: QuizSubjectEntityMapper,
    private val questionEntityMapper: QuizQuestionEntityMapper
) : QuizSubjectsRepository(), QuizApiHelper {

    override suspend fun retrieveSubjects(): List<QuizSubjectDomainModel> {
        retrofitCall { questionsApi.retrieveQuestions() }
            .onSuccess { data ->
                saveRemoteDataToLocal(data)
            }
        return retrieveLocalSubjects()
    }

    private suspend fun saveRemoteDataToLocal(subjectsList: List<QuizSubjectDto>) {
        val subjectEntities = subjectEntityMapper.toEntityList(
            subjectDtoMapper.toDomainList(subjectsList)
        )
        subjectsDao.insertSubjects(subjectEntities)
        subjectsList.forEach { subjectDto ->
            val questions = subjectDto.questions.map { questionDto ->
                questionDtoMapper.isLastQuestion = {
                    questionDto.questionIndex + 1 == subjectDto.questionsCount
                }
                questionDtoMapper.subjectTitle = {
                    subjectDto.quizTitle
                }
                questionEntityMapper.toEntity(
                    questionDtoMapper.toDomain(
                        questionDto
                    )
                )
            }
            questionsDao.insertQuestionsList(questions)
        }
    }

    override suspend fun retrieveLocalSubjects(): List<QuizSubjectDomainModel> {
        return subjectEntityMapper.toDomainList(subjectsDao.getSubjects())
    }

    override suspend fun retrieveLocalSubjectByTitle(quizTitle: String): QuizSubjectDomainModel {
        return subjectEntityMapper.toDomain(subjectsDao.getSubjectByTitle(quizTitle))
    }
}
