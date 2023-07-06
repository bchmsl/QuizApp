package com.space.quizapp.data.repository.quiz

import com.space.common.extensions.utils.onSuccess
import com.space.common.model.question.data.QuizQuestionEntityMapper
import com.space.common.model.question.data.QuizSubjectEntityMapper
import com.space.common.model.subject.domain.QuizSubjectDomainModel
import com.space.common.util.QuizApiHelper
import com.space.quizapp.data.local.database.dao.QuizQuestionsDao
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.remote.model.QuizSubjectDto
import com.space.quizapp.data.remote.model.mapper.QuizQuestionDtoMapper
import com.space.quizapp.data.remote.model.mapper.QuizSubjectDtoMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository

class QuizSubjectsRepositoryImpl(
    private val questionsApi: QuizQuestionsApiService,
    private val subjectsDao: QuizSubjectsDao,
    private val questionsDao: QuizQuestionsDao,
    private val subjectDtoMapper: QuizSubjectDtoMapper,
    private val questionDtoMapper: QuizQuestionDtoMapper,
    private val subjectEntityMapper: com.space.common.model.question.data.QuizSubjectEntityMapper,
    private val questionEntityMapper: com.space.common.model.question.data.QuizQuestionEntityMapper
) : QuizSubjectsRepository(), com.space.common.util.QuizApiHelper {

    override suspend fun retrieveSubjects(): List<com.space.common.model.subject.domain.QuizSubjectDomainModel> {
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

    override suspend fun retrieveLocalSubjects(): List<com.space.common.model.subject.domain.QuizSubjectDomainModel> {
        return subjectEntityMapper.toDomainList(subjectsDao.getSubjects())
    }

    override suspend fun retrieveLocalSubjectByTitle(quizTitle: String): com.space.common.model.subject.domain.QuizSubjectDomainModel {
        return subjectEntityMapper.toDomain(subjectsDao.getSubjectByTitle(quizTitle))
    }
}
