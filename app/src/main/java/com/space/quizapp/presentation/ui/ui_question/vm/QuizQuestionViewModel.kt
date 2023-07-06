package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.domain.usecase.questions.FinishAlertUseCase
import com.space.quizapp.domain.usecase.questions.GetQuestionsCountUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizAnswerUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionUiMapper
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import kotlinx.coroutines.Dispatchers.IO

class QuizQuestionViewModel(
    private val getNextQuestionUC: QuizGetNextQuestionUseCase,
    private val checkAnswersUC: QuizCheckAnswersUseCase,
    private val saveUserPointsUC: QuizSaveUserPointsUseCase,
    private val questionsCountUC: GetQuestionsCountUseCase,
    private val updateGpaUC: QuizUpdateGpaUseCase,
    private val finishAlertUC: FinishAlertUseCase,
    private val questionMapper: QuizQuestionUiMapper,
    private val answerMapper: QuizAnswerUiMapper
) : QuizBaseViewModel() {

    private var answers = listOf<QuizQuestionUiModel.QuizAnswerUiModel>()
    private var question: QuizQuestionUiModel? = null
    val questionState by QuizLiveDataDelegate<QuizQuestionUiModel?>(null)
    val answersListState by QuizLiveDataDelegate<
            List<QuizQuestionUiModel.QuizAnswerUiModel>?
            >(emptyList())
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

    fun navigateToHome() {
        navigate(QuizFragmentDirections.HOME)
    }
}
