package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.data.repository.quiz.QuizQuestionsRepositoryImpl
import com.space.quizapp.domain.model.quiz.QuizQuestionDomainModel
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizCheckAnswersUseCase(
    private val questionsRepository: QuizQuestionsRepositoryImpl,
    private val addPointsToSubjectUseCase: AddPointsToSubjectUseCase,
) : QuizBaseUseCase<CheckAnswerParams, Boolean>() {

    override suspend fun invoke(params: CheckAnswerParams?): Boolean {
        val question = questionsRepository.getNextQuestion(params!!.subjectTitle)
        questionsRepository.updateQuestion(question!!.copy(isAnswered = true))

        val isCorrect = params.answerModel.isCorrect
        if (isCorrect) addPointsToSubjectUseCase(AddPointsParams(question.subjectTitle, 1))
        else addPointsToSubjectUseCase(AddPointsParams(question.subjectTitle, 0))
        return params.answerModel.isCorrect
    }
}

data class CheckAnswerParams(
    val answerModel: QuizQuestionDomainModel.QuizAnswerDomainModel,
    val subjectTitle: String
)
