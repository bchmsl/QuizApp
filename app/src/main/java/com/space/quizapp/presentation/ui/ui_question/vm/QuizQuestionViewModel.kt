package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.domain.usecase.questions.CheckAnswerParams
import com.space.quizapp.domain.usecase.questions.GetQuestionsCountUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.SaveUserPointsRequest
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizAnswerUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionUiMapper
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState
import kotlinx.coroutines.Dispatchers.IO

class QuizQuestionViewModel(
    private val getNextQuestionUC: QuizGetNextQuestionUseCase,
    private val checkAnswersUC: QuizCheckAnswersUseCase,
    private val saveUserPointsUC: QuizSaveUserPointsUseCase,
    private val questionsCountUC: GetQuestionsCountUseCase,
    private val updateGpaUC: QuizUpdateGpaUseCase,
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
    val isFinished by QuizLiveDataDelegate(false)

    fun getNextQuestion(subjectTitle: String) {
        executeAsync(IO) {
            val question = getNextQuestionUC(subjectTitle)
            if (question == null) {
                questionState.post(null)
                isFinished.post(true)
                pointsState.value?.let { saveUserSubject(subjectTitle, it) }
                pointsState.post(0)
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
            val isCorrect = checkAnswersUC(
                CheckAnswerParams(
                    answerMapper.toDomain(submittedAnswer),
                    subjectTitle
                )
            )
            val checkedAnswersList = answers
            if (isCorrect) {
                checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                    QuizAnswerSelectedState.ANSWER_SELECTED_CORRECT
                addPoints()
            } else {
                checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                    QuizAnswerSelectedState.ANSWER_SELECTED_POSITIVE
                checkedAnswersList.find { it == submittedAnswer }?.answerSelectedState =
                    QuizAnswerSelectedState.ANSWER_SELECTED_NEGATIVE
            }
            answersListState.post(checkedAnswersList)
        }
    }

    private fun addPoints() {
        question?.points?.let { pointsState.post(pointsState.value?.plus(it) ?: 0) }
    }

    fun saveUserSubject(subjectTitle: String, points: Int) {
        executeAsync(IO) {
            saveUserPointsUC(SaveUserPointsRequest(subjectTitle, points))
            updateGpaUC()
        }
    }

    fun getQuestionCount(subjectTitle: String) {
        executeAsync {
            questionCount.post(questionsCountUC(subjectTitle))
        }
    }
}
