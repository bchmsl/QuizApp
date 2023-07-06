package com.space.quiz_impl.presentation.quiz.viewmodel

import com.space.common.extensions.coroutines.executeAsync
import com.space.common.util.QuizLiveDataDelegate
import com.space.navigation_api.AppNavigator
import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.domain.usecase.GetQuestionsCountUseCase
import com.space.quiz_impl.domain.usecase.QuizCheckAnswersUseCase
import com.space.quiz_impl.domain.usecase.QuizGetNextQuestionUseCase
import com.space.quiz_impl.domain.usecase.QuizSaveUserPointsUseCase
import com.space.quiz_impl.domain.usecase.QuizUpdateGpaUseCase
import com.space.quiz_impl.presentation.quiz.model.QuizAnswerUiMapper
import com.space.quiz_impl.presentation.quiz.model.QuizQuestionUiMapper
import com.space.quiz_impl.presentation.quiz.model.QuizQuestionUiModel
import kotlinx.coroutines.Dispatchers.IO

class QuizQuestionViewModel(
    private val updateGpaUC: QuizUpdateGpaUseCase,
    private val saveUserPointsUC: QuizSaveUserPointsUseCase,

    private val getNextQuestionUC: QuizGetNextQuestionUseCase,
    private val checkAnswersUC: QuizCheckAnswersUseCase,
    private val questionsCountUC: GetQuestionsCountUseCase,
    private val finishAlertUC: FinishAlertUseCase,

    private val questionMapper: QuizQuestionUiMapper,
    private val answerMapper: QuizAnswerUiMapper,

    private val appNavigator: AppNavigator
) : com.space.common.base.viewmodel.QuizBaseViewModel() {

    private var answers = listOf<QuizQuestionUiModel.QuizAnswerUiModel>()
    private var question: QuizQuestionUiModel? = null
    val questionState by QuizLiveDataDelegate<QuizQuestionUiModel?>(null)
    val answersListState by QuizLiveDataDelegate<List<QuizQuestionUiModel.QuizAnswerUiModel>?>(
        emptyList()
    )
    val pointsState by QuizLiveDataDelegate(0)
    val questionCount by QuizLiveDataDelegate(0)
    val finishAlertState by QuizLiveDataDelegate<FinishAlertUseCase.FinishAlertResponse?>(null)

    fun getNextQuestion(subjectTitle: String) {
        executeAsync(IO) {
            val question = getNextQuestionUC(subjectTitle)
            if (question == null) {
                questionState.post(null)
                finishAlertState.post(
                    finishAlertUC(
                        FinishAlertUseCase.FinishAlertParams(
                            pointsState.value,
                            questionCount.value
                        )
                    )
                )
                pointsState.value?.let { saveUserSubject(subjectTitle, it) }
                return@executeAsync
            }
            this@QuizQuestionViewModel.question = questionMapper.toUi(question)
            questionState.post(this@QuizQuestionViewModel.question)
            this@QuizQuestionViewModel.question?.let {
                answers = it.answers
                answersListState.post(answers)
            }
        }
    }

    fun checkAnswer(
        submittedAnswer: QuizQuestionUiModel.QuizAnswerUiModel,
        subjectTitle: String
    ) {
        executeAsync(IO) {
            val checkAnswersResponse = checkAnswersUC(
                QuizCheckAnswersUseCase.CheckAnswerParams(
                    answerMapper.toDomain(submittedAnswer),
                    answersListState.value?.map { answerMapper.toDomain(it) } ?: emptyList(),
                    subjectTitle
                )
            )
            answersListState.post(checkAnswersResponse.answersList.map { answerMapper.toUi(it) })
            if (checkAnswersResponse.isCorrect) addPoints()
        }
    }

    fun getQuestionCount(subjectTitle: String) {
        executeAsync {
            pointsState.post(0)
            questionCount.post(questionsCountUC(subjectTitle))
            getNextQuestion(subjectTitle)
        }
    }

    private fun addPoints() {
        question?.points?.let { pointsState.post(pointsState.value?.plus(it) ?: 0) }
    }

    private fun saveUserSubject(subjectTitle: String, points: Int) {
        executeAsync(IO) {
            saveUserPointsUC(QuizSaveUserPointsUseCase.SaveUserPointsParams(subjectTitle, points))
            updateGpaUC()
        }
    }

    fun navigateToHome(){
        appNavigator.navigateToHome()
    }
}
