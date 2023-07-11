package com.space.quiz_impl.presentation.quiz.viewmodel

import com.space.common.extensions.coroutines.executeAsync
import com.space.common.model.question.model.AnswerUiMapper
import com.space.common.model.question.model.QuestionUiMapper
import com.space.common.model.question.model.QuestionUiModel
import com.space.common.util.LiveDataDelegate
import com.space.navigation_api.AppNavigator
import com.space.quiz_impl.domain.usecase.CheckAnswersUseCase
import com.space.quiz_impl.domain.usecase.FinishAlertUseCase
import com.space.quiz_impl.domain.usecase.GetNextQuestionUseCase
import com.space.quiz_impl.domain.usecase.GetQuestionsCountUseCase
import com.space.quiz_impl.domain.usecase.SaveUserPointsUseCase
import com.space.quiz_impl.domain.usecase.UpdateGpaUseCase
import kotlinx.coroutines.Dispatchers.IO

class QuestionViewModel(
    private val updateGpaUC: UpdateGpaUseCase,
    private val saveUserPointsUC: SaveUserPointsUseCase,

    private val getNextQuestionUC: GetNextQuestionUseCase,
    private val checkAnswersUC: CheckAnswersUseCase,
    private val questionsCountUC: GetQuestionsCountUseCase,
    private val finishAlertUC: FinishAlertUseCase,

    private val questionMapper: QuestionUiMapper,
    private val answerMapper: AnswerUiMapper,

    private val appNavigator: AppNavigator
) : com.space.common.base.viewmodel.BaseViewModel() {

    private var answers = listOf<QuestionUiModel.QuizAnswerUiModel>()
    private var question: QuestionUiModel? = null
    val questionState by LiveDataDelegate<QuestionUiModel?>(null)
    val answersListState by LiveDataDelegate<List<QuestionUiModel.QuizAnswerUiModel>?>(
        emptyList()
    )
    val pointsState by LiveDataDelegate(0)
    val questionCount by LiveDataDelegate(0)
    val finishAlertState by LiveDataDelegate<FinishAlertUseCase.FinishAlertResponse?>(null)

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
            this@QuestionViewModel.question = questionMapper.toUi(question)
            questionState.post(this@QuestionViewModel.question)
            this@QuestionViewModel.question?.let {
                answers = it.answers
                answersListState.post(answers)
            }
        }
    }

    fun checkAnswer(
        submittedAnswer: QuestionUiModel.QuizAnswerUiModel,
        subjectTitle: String
    ) {
        executeAsync(IO) {
            val checkAnswersResponse = checkAnswersUC(
                CheckAnswersUseCase.CheckAnswerParams(
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
            saveUserPointsUC(SaveUserPointsUseCase.SaveUserPointsParams(subjectTitle, points))
            updateGpaUC()
        }
    }

    fun navigateToHome(){
        appNavigator.navigateToHome()
    }
}
