package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.resource.QuizResource
import com.space.quizapp.domain.usecase.questions.questions.QuizQuestionsUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionsUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionDomainUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizQuestionViewModel(
    private val questionsUC: QuizQuestionsUseCase,
    private val questionDomainUiMapper: QuizQuestionDomainUiMapper
) : QuizBaseViewModel() {

    private val _questionsState = MutableStateFlow<List<QuizQuestionsUiModel.Question>>(emptyList())
    val questionsState get() = _questionsState.asStateFlow()

    fun retrieveQuestions(title: String) {
        executeAsync(IO) {
            questionsUC().collect {
                when (it) {
                    is QuizResource.Success -> {
                        val questions = it.data.find {
                            it.quizTitle == title
                        }!!.questions.map {
                            questionDomainUiMapper(it)
                        }
                        _questionsState.emit(questions)
                    }
                    is QuizResource.Error -> emitError(it.error)
                    else -> {}
                }
            }
        }
    }
}
