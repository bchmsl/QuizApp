package com.space.quizapp.domain.usecase.questions

import com.space.quizapp.domain.repository.quiz.QuizQuestionsRepository
import com.space.quizapp.domain.repository.quiz.QuizSubjectsRepository
import com.space.quizapp.domain.repository.user.QuizUserDataRepository
import com.space.quizapp.domain.usecase.user.subject.QuizReadUserSubjectsUseCase

class QuizUpdateGpaUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val subjectsRepository: QuizSubjectsRepository,
    private val questionsRepository: QuizQuestionsRepository,
    private val userDataRepository: QuizUserDataRepository
) : com.space.common.base.usecase.QuizBaseUseCase<Unit, Unit>() {
    override suspend fun invoke(params: Unit?) {
        val userSubjects = readUserSubjectsUC()
        val scorePercentages = mutableListOf<Float>()
        userSubjects.forEach { userSubject ->
            val subject = subjectsRepository.retrieveLocalSubjectByTitle(userSubject.quizTitle)
            val questions = questionsRepository.getQuestionsBySubjectTitle(subject.quizTitle)
            val maxPoints = questions.sumOf { it.points }
            val userScorePercent =
                userSubject.score.toFloat() / maxPoints
            scorePercentages.add(userScorePercent)
        }
        val gpa = scorePercentages.average() * 4.0f
        userDataRepository.updateGPA(readUserDataUC().username, gpa)
    }
}
