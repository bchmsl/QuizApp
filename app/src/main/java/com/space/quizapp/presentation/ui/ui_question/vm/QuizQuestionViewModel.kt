package com.space.quizapp.presentation.ui.ui_question.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.usecase.questions.CheckAnswerParams
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizGetPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizSaveUserSubjectUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizAnswerUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionUiMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class QuizQuestionViewModel(
    private val getNextQuestionUC: QuizGetNextQuestionUseCase,
    private val checkAnswersUC: QuizCheckAnswersUseCase,
    private val getPointsUC: QuizGetPointsUseCase,
    private val saveUserSubjectUC: QuizSaveUserSubjectUseCase,
    private val saveUserPointsUC: QuizSaveUserPointsUseCase,
    private val updateGpaUC: QuizUpdateGpaUseCase,
    private val questionMapper: QuizQuestionUiMapper,
    private val answerMapper: QuizAnswerUiMapper
) : QuizBaseViewModel() {

    private val _questionState =
        MutableStateFlow<QuizQuestionUiModel?>(null)
    val questionState get() = _questionState.asStateFlow()

    private val _checkedAnswerState =
        MutableLiveData<Boolean?>(null)
    val checkedAnswerState: LiveData<Boolean?> get() = _checkedAnswerState

    private val _pointsState = MutableStateFlow<Int?>(null)
    val pointsState get() = _pointsState.asStateFlow()

    fun getNextQuestion(subjectTitle: String) {
        executeAsync(IO) {
            val question = getNextQuestionUC(subjectTitle)
            if (question == null) {
                _pointsState.emit(getPointsUC())
                saveUserPointsUC(subjectTitle)
                return@executeAsync
            }
            _questionState.emit(questionMapper.toUi(question))
        }
    }

    fun checkAnswer(
        submittedAnswer: QuizQuestionUiModel.QuizAnswerUiModel,
        subjectTitle: String
    ) {
        executeAsync(IO) {
            val bool = checkAnswersUC(
                CheckAnswerParams(
                    answerMapper.toDomain(submittedAnswer),
                    subjectTitle
                )
            )
            withContext(Main) {
                _checkedAnswerState.value = bool
            }
        }
    }

    fun saveUserSubject(quizTitle: String, score: Int) {
        executeAsync(IO) {
            saveUserSubjectUC(QuizUserSubjectDomainModel(quizTitle = quizTitle, score = score))
            updateGpaUC()
        }
    }
}
