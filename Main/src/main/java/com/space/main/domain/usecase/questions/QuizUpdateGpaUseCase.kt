package com.space.main.domain.usecase.questions

import com.space.main.domain.repository.quiz.QuizQuestionsRepository
import com.space.main.domain.repository.quiz.QuizSubjectsRepository
import com.space.main.domain.repository.user.QuizUserDataRepository
import com.space.main.domain.usecase.user.QuizReadUserDataUseCase
import com.space.main.domain.usecase.user.subject.QuizReadUserSubjectsUseCase
import com.space.quiz_api.UpdateUserGpa

class QuizUpdateGpaUseCase(
    private val readUserDataUC: QuizReadUserDataUseCase,
    private val readUserSubjectsUC: QuizReadUserSubjectsUseCase,
    private val subjectsRepository: QuizSubjectsRepository,
    private val questionsRepository: QuizQuestionsRepository,
    private val userDataRepository: QuizUserDataRepository
) : UpdateUserGpa {
    override suspend fun invoke() {
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
