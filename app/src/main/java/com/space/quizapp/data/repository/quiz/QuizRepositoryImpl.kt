package com.space.quizapp.data.repository.quiz

import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.common.util.ApiHelper
import com.space.quizapp.data.local.database.dao.QuizSubjectsDao
import com.space.quizapp.data.local.database.model.quiz.mapper.question.QuizQuestionDomainEntityMapper
import com.space.quizapp.data.local.database.model.quiz.mapper.subject.QuizSubjectDomainEntityMapper
import com.space.quizapp.data.remote.model.mapper.subject.QuizSubjectDtoDomainMapper
import com.space.quizapp.data.remote.service.QuizQuestionsApiService
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.model.quiz.QuizSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository

class QuizRepositoryImpl(
    private val api: QuizQuestionsApiService,
    private val apiHelper: ApiHelper,
    private val dao: QuizSubjectsDao,
    private val quizSubjectDtoDomainMapper: QuizSubjectDtoDomainMapper,
    private val quizSubjectDomainEntityMapper: QuizSubjectDomainEntityMapper,
    private val quizQuestionDomainEntityMapper: QuizQuestionDomainEntityMapper
) : QuizRepository {

    override suspend fun retrieveQuestions(): QuizResource<List<QuizSubjectDomainModel>> {
        return apiHelper.retrofitCall(
            { data -> data.map { quizSubjectDtoDomainMapper(it) } },
            { api.retrieveQuestions() }
        )
    }


    override suspend fun updateLocalQuiz(
        subjects: List<QuizSubjectDomainModel>,
        questions: List<QuizQuestionDomainModel>
    ) {
        dao.insertSubjects(subjects.map { quizSubjectDomainEntityMapper(it) })
        dao.insertQuestions(questions.map { quizQuestionDomainEntityMapper(it) })
    }

}
