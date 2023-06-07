package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.domain.usecase.questions.check_answers.CheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.next_question.GetNextQuestionUseCase
import com.space.quizapp.domain.usecase.quiz.retrieve_questions.QuizRetrieveQuestionsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.answer.QuizAnswerUiDomainMapper
import com.space.quizapp.presentation.model.quiz.mapper.question.QuizQuestionDomainUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizQuestionViewModel(
    private val getNextQuestionUC: GetNextQuestionUseCase,
    private val checkAnswersUC: CheckAnswersUseCase,
    private val retrieveQuestionsUC: QuizRetrieveQuestionsUseCase,
    private val quizQuestionDomainUiMapper: QuizQuestionDomainUiMapper,
    private val quizAnswerUiDomainMapper: QuizAnswerUiDomainMapper,
    private val quizAnswerDomainUiMapper: QuizAnswerDomainUiMapper
) : QuizBaseViewModel() {

    private val _questionState = MutableStateFlow<QuizQuestionUiModel?>(null)
    val questionState get() = _questionState.asStateFlow()

    private val _checkedAnswersState =
        MutableStateFlow<List<QuizQuestionUiModel.QuizAnswerUiModel>?>(null)
    val checkedAnswersState get() = _checkedAnswersState.asStateFlow()

    fun getNextQuestion() {
        executeAsync(Main) {
            val question = getNextQuestionUC()
            _questionState.emit(quizQuestionDomainUiMapper(question))
        }
    }

    fun checkAnswer(
        submittedAnswer: QuizQuestionUiModel.QuizAnswerUiModel
    ) {
        executeAsync(Main) {
            val answersList = checkAnswersUC(quizAnswerUiDomainMapper(submittedAnswer))
            _checkedAnswersState.emit(answersList.map { quizAnswerDomainUiMapper(it) })
        }
    }

    fun retrieveQuestions(subjectId: Int) {
        executeAsync(IO) {
            retrieveQuestionsUC(subjectId)
            getNextQuestion()
        }
    }
}
