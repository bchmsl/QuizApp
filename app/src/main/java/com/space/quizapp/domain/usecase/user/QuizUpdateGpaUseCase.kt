package com.space.quizapp.domain.usecase.user

import com.space.quizapp.domain.model.user.QuizUserSubjectDomainModel
import com.space.quizapp.domain.repository.quiz.QuizRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.base.QuizBaseUseCase

class QuizUpdateGpaUseCase(
    private val getUserTokenUseCase: QuizBaseUseCase<Unit, String>,
    private val readUserSubjectsUseCase: QuizBaseUseCase<Unit, List<QuizUserSubjectDomainModel>>,
    private val subjectsRepository: QuizRepository,
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
        userDataRepository.updateGPA(getUserTokenUseCase(), gpa)
    }
}
