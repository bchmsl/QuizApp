package com.space.main_impl.domain.usecase.questions

import com.space.common.base.usecase.QuizBaseUseCase
import com.space.main_impl.domain.repository.quiz.QuizQuestionsRepository
import com.space.main_impl.domain.repository.quiz.QuizSubjectsRepository
import com.space.main_impl.domain.repository.user.QuizUserDataRepository
import com.space.main_impl.domain.usecase.user.QuizReadUserDataUseCase
import com.space.main_impl.domain.usecase.user.subject.QuizReadUserSubjectsUseCase

class QuizUpdateGpaUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val subjectsRepository: QuizSubjectsRepository,
    private val questionsRepository: QuizQuestionsRepository,
    private val userDataRepository: QuizUserDataRepository
) : QuizBaseUseCase<Unit, Unit>() {
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
