package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.extensions.utils.onError
import com.space.quizapp.common.extensions.utils.onSuccess
import com.space.quizapp.domain.usecase.questions.get_questions.QuizQuestionsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel
import com.space.quizapp.presentation.model.quiz.mapper.question.QuizQuestionDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.subject.QuizSubjectDomainUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.subject.QuizSubjectUiDomainMapper
import com.space.quizapp.presentation.ui.ui_question.manager.QuestionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class QuizQuestionViewModel(
    private val questionsUC: QuizQuestionsUseCase,
    private val quizSubjectDomainUiMapper: QuizSubjectDomainUiMapper,
    private val quizQuestionDomainUiMapper: QuizQuestionDomainUiMapper,
    private val quizSubjectUiDomainMapper: QuizSubjectUiDomainMapper,
    private val questionManager: QuestionManager
) : QuizBaseViewModel() {

    private val _questionsState = MutableStateFlow<QuizQuestionUiModel?>(null)
    val questionsState get() = _questionsState.asStateFlow()

    private val _checkedAnswerState =
        MutableStateFlow<List<QuizQuestionUiModel.QuizAnswerUiModel>?>(null)
    val checkedAnswerState get() = _checkedAnswerState.asStateFlow()

    fun getNextQuestion() {
        executeAsync {
            questionManager.getNextQuestion()?.let {
                _questionsState.emit(quizQuestionDomainUiMapper(it))
            }
        }
    }

    fun checkAnswer(
        submittedAnswer: String,
        answers: MutableList<QuizQuestionUiModel.QuizAnswerUiModel>
    ) {
        executeAsync {
            questionManager.getCheckedAnswersList(submittedAnswer, answers)
        }
    }

    private suspend fun retrieveQuestions(): Flow<List<QuizSubjectUiModel>> = flow {
        val resource = questionsUC()
        resource.onSuccess { data ->
            emit(data.map { quizSubjectDomainUiMapper(it) })
        }.onError { error ->
            emitError(error)
        }
    }

    fun getQuestions(category: String) {
        executeAsync {
            retrieveQuestions().collect { questions ->
                with(questionManager) {
                    submitQuestionsList(questions.map { quizSubjectUiDomainMapper(it) })
                    selectCategory(category)
                }
            }
            getNextQuestion()
        }
    }
}
