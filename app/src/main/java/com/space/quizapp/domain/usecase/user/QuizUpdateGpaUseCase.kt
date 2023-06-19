package com.space.quizapp.domain.usecase.user

import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase

class QuizUpdateGpaUseCase(
    private val readUserTokenUseCase: QuizReadUserTokenUseCase,
    private val readUserSubjectsUseCase: QuizReadUserSubjectsUseCase,
    private val subjectsRepository: QuizSubjectsRepository,
    private val userDataRepository: QuizUserDataRepository
) : QuizBaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        val userSubjects = readUserSubjectsUseCase()
        val scorePercentages = mutableListOf<Float>()
        userSubjects.forEach { userSubject ->
            val subject = subjectsRepository.retrieveLocalSubjectByTitle(userSubject.quizTitle)
            val userScorePercent = userSubject.score.toFloat().div(subject.questionsCount)
            scorePercentages.add(userScorePercent)
        }
        val gpa = scorePercentages.average() * 4.0f
        userDataRepository.updateGPA(readUserTokenUseCase(), gpa)
    }
}
