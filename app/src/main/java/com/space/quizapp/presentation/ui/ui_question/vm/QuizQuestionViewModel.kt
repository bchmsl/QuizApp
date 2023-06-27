package com.space.quizapp.presentation.ui.ui_question.vm

import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.util.QuizLiveDataDelegate
import com.space.quizapp.common.util.postValue
import com.space.quizapp.common.util.postValueAsync
import com.space.quizapp.common.util.postValueAsyncNonNull
import com.space.quizapp.common.util.postValueNonNull
import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.usecase.questions.CheckAnswerParams
import com.space.quizapp.domain.usecase.questions.GetQuestionsCountUseCase
import com.space.quizapp.domain.usecase.questions.QuizCheckAnswersUseCase
import com.space.quizapp.domain.usecase.questions.QuizGetPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizResetUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.QuizSaveUserPointsUseCase
import com.space.quizapp.domain.usecase.questions.next_question.QuizGetNextQuestionUseCase
import com.space.quizapp.domain.usecase.user.QuizUpdateGpaUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizSaveUserSubjectUseCase
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.model.quiz.QuizQuestionUiModel
import com.space.quizapp.presentation.model.quiz.mapper.QuizAnswerUiMapper
import com.space.quizapp.presentation.model.quiz.mapper.QuizQuestionUiMapper
import com.space.quizapp.presentation.ui.ui_question.util.QuizAnswerSelectedState
import kotlinx.coroutines.Dispatchers.IO

class QuizQuestionViewModel(
    private val getNextQuestionUC: QuizGetNextQuestionUseCase,
    private val checkAnswersUC: QuizCheckAnswersUseCase,
    private val getPointsUC: QuizGetPointsUseCase,
    private val resetUserPointsUC: QuizResetUserPointsUseCase,
    private val saveUserSubjectUC: QuizSaveUserSubjectUseCase,
    private val saveUserPointsUC: QuizSaveUserPointsUseCase,
    private val questionsCountUC: GetQuestionsCountUseCase,
    private val updateGpaUC: QuizUpdateGpaUseCase,
    private val questionMapper: QuizQuestionUiMapper,
    private val answerMapper: QuizAnswerUiMapper
) : QuizBaseViewModel() {

    private val answers = mutableListOf<QuizQuestionUiModel.QuizAnswerUiModel>()

    val questionState by QuizLiveDataDelegate<QuizQuestionUiModel?>(null)
    val answersListState by QuizLiveDataDelegate<
            List<QuizQuestionUiModel.QuizAnswerUiModel>?
            >(emptyList())
    val pointsState by QuizLiveDataDelegate<Int?>(null)
    val questionCount by QuizLiveDataDelegate(0)
    val isFinished by QuizLiveDataDelegate(false)

    fun resetUserPoints() {
        executeAsync(IO) {
            resetUserPointsUC()
        }
    }

    fun getNextQuestion(subjectTitle: String) {
        executeAsync(IO) {
            postValueAsync(questionState) {
                val question = getNextQuestionUC(subjectTitle)
                if (question == null) {
                    saveUserPointsUC(subjectTitle)
                    postValueAsyncNonNull(isFinished) { true }
                    return@postValueAsync null
                }
                postValueAsync(pointsState) { getPointsUC() }
                answers.clear()
                answers.addAll(answerMapper.toUiList(question.answers))
                submitAnswers()
                questionMapper.toUi(question)
            }
        }
    }

    private fun submitAnswers() {
        postValueNonNull(answersListState) {
            answers
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
            } else {
                checkedAnswersList.find { it.isCorrect }?.answerSelectedState =
                    QuizAnswerSelectedState.ANSWER_SELECTED_POSITIVE
                checkedAnswersList.find { it == submittedAnswer }?.answerSelectedState =
                    QuizAnswerSelectedState.ANSWER_SELECTED_NEGATIVE
            }
            postValue(answersListState) { checkedAnswersList }
        }
    }

    fun saveUserSubject(quizTitle: String, score: Int) {
        executeAsync(IO) {
            saveUserSubjectUC(QuizUserSubjectDomainModel(quizTitle = quizTitle, score = score))
            updateGpaUC()
        }
    }

    fun getQuestionCount(subjectTitle: String) {
        executeAsync {
            postValueAsyncNonNull(questionCount) {
                questionsCountUC(subjectTitle)
            }
        }
    }
}
