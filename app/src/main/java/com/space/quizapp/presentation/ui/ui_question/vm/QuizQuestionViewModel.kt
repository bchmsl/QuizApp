package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.extensions.utils.onError
import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.domain.usecase.questions.get_questions.QuizQuestionsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionsDomainUiMapper
import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManager
import com.space.quizapp.presentation.ui.ui_question.model.AnswerModel
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
            questionManager.getCheckedAnswersList(submittedAnswer, answers)
        }
    }

    private suspend fun retrieveQuestions(): Flow<List<QuizQuestionsUiModel>> = flow {
        questionsUC().collect { resource ->
            resource.onSuccess { data ->
                emit(data.map { questionsDomainUiMapper(it) })

            }.onError { error ->
                emitError(error)
            }
        }
    }

    fun getQuestions(category: String) {
        executeAsync {
            retrieveQuestions().collect { questions ->
                with(questionManager) {
                    submitQuestionsList(questions)
                    selectCategory(category)
                }
            }
            getNextQuestion()
        }
    }
}
