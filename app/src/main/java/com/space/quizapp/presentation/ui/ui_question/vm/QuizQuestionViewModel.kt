package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionsDomainUiMapper
import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManager
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class QuizQuestionViewModel(
    private val questionsUC: QuizQuestionsUseCase,
    private val questionsDomainUiMapper: QuizQuestionsDomainUiMapper,
    private val questionManager: QuestionManager
) : QuizBaseViewModel() {

    private val _questionsState = MutableStateFlow<QuizQuestionsUiModel.Question?>(null)
    val questionsState get() = _questionsState.asStateFlow()

    private val _checkedAnswerState = MutableStateFlow<List<AnswerModel>?>(null)
    val checkedAnswerState get() = _checkedAnswerState.asStateFlow()

    fun getNextQuestion() {
        executeAsync {
            questionManager.getNextQuestion()?.let {
                _questionsState.emit(it)
            }
        }
    }

    fun checkAnswer(submittedAnswer: String, answers: MutableList<AnswerModel>) {
        executeAsync {
            questionManager.getCorrectAnswer()?.let { correctAnswer ->
                val correctAnswerModel = answers.find { it.answerOption == correctAnswer }
                answers[answers.indexOf(correctAnswerModel)] =
                    correctAnswerModel!!.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT)
                if (submittedAnswer != correctAnswer) {
                    val wrongAnswerModel = answers.find { it.answerOption == submittedAnswer }
                    answers[answers.indexOf(wrongAnswerModel)] =
                        wrongAnswerModel!!.copy(selectedState = QuizAnswerSelectedState.ANSWER_SELECTED_INCORRECT)
                }
                _checkedAnswerState.emit(answers)
            }
        }
    }

    private fun retrieveQuestions(): Flow<List<QuizQuestionsUiModel>> = flow {
        questionsUC().collect { resource ->
            when (resource) {
                is QuizResource.Success -> {
                    emit(resource.data.map { questionsDomainUiMapper(it) })
                }
                is QuizResource.Error -> emitError(resource.error)
                else -> {}
            }
        }
    }

    fun getQuestions(category: String) {
        executeAsync {
            retrieveQuestions().collect { questions ->
                with(questionManager) {
                    submitQuestionsList(questions)
                    selectCategory(category)
                    getNextQuestion()
                }
            }
        }
    }
}
