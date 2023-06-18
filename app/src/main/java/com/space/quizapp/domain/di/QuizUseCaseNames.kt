package com.space.quizapp.domain.di

import org.koin.core.qualifier.named

object QuizUseCaseNames {
    val GET_NEXT_QUESTION = named("getNextQuestion")
    val CHECK_ANSWERS = named("checkAnswers")
    val GET_POINTS = named("getPoints")
    val RETRIEVE_QUESTIONS = named("retrieveQuestions")
    val RETRIEVE_SUBJECTS = named("retrieveSubjects")
    val READ_USER_TOKEN = named("readUserToken")
    val SAVE_USER_TOKEN = named("saveUserToken")
    val READ_USER_DATA = named("readUserData")
    val SAVE_USER_DATA = named("saveUserData")
    val SAVE_USER_SUBJECT = named("saveUserSubject")
    val READ_USER_SUBJECTS = named("readUserSubjects")
}
