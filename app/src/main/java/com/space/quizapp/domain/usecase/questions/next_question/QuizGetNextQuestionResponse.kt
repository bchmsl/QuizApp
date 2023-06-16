package com.space.quizapp.domain.usecase.questions.next_question

data class QuizGetNextQuestionResponse<T>(val questionModel: T, val isLastQuestion: Boolean)
