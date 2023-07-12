package com.space.main.data.repository.quiz

import com.space.common.extensions.utils.onSuccess
import com.space.common.model.question.data.QuestionEntityMapper
import com.space.common.model.subject.data.SubjectEntityMapper
import com.space.common.model.subject.domain.SubjectDomainModel
import com.space.common.util.ApiHelper
import com.space.main.data.local.database.dao.QuestionsDao
import com.space.main.data.local.database.dao.SubjectsDao
import com.space.main.data.remote.model.SubjectDto
import com.space.main.data.remote.model.mapper.QuestionDtoMapper
import com.space.main.data.remote.model.mapper.SubjectDtoMapper
import com.space.main.data.remote.service.QuestionsApiService
import com.space.main.domain.repository.quiz.SubjectsRepository

class SubjectsRepositoryImpl(
    private val questionsApi: QuestionsApiService,
    private val subjectsDao: SubjectsDao,
    private val questionsDao: QuestionsDao,
    private val subjectDtoMapper: SubjectDtoMapper,
    private val questionDtoMapper: QuestionDtoMapper,
    private val subjectEntityMapper: SubjectEntityMapper,
    private val questionEntityMapper: QuestionEntityMapper
) : SubjectsRepository(), ApiHelper {

    override suspend fun retrieveSubjects(): List<SubjectDomainModel> {
        retrofitCall { questionsApi.retrieveQuestions() }
            .onSuccess { data ->
                saveRemoteDataToLocal(data)
            }
        return retrieveLocalSubjects()
    }

    private suspend fun saveRemoteDataToLocal(subjectsList: List<SubjectDto>) {
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

    override suspend fun retrieveLocalSubjects(): List<SubjectDomainModel> {
        return subjectEntityMapper.toDomainList(subjectsDao.getSubjects())
    }

    override suspend fun retrieveLocalSubjectByTitle(quizTitle: String): SubjectDomainModel {
        return subjectEntityMapper.toDomain(subjectsDao.getSubjectByTitle(quizTitle))
    }
}
