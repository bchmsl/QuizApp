package com.space.main.domain.usecase.questions

import com.space.main.domain.repository.quiz.QuestionsRepository
import com.space.main.domain.repository.quiz.SubjectsRepository
import com.space.main.domain.repository.user.UserDataRepository
import com.space.main.domain.usecase.user.ReadUserDataUseCase
import com.space.main.domain.usecase.user.subject.ReadUserSubjectsUseCase
import com.space.quiz_api.UpdateUserGpa

class UpdateGpaUseCase(
    private val readUserDataUC: ReadUserDataUseCase,
    private val readUserSubjectsUC: ReadUserSubjectsUseCase,
    private val subjectsRepository: SubjectsRepository,
    private val questionsRepository: QuestionsRepository,
    private val userDataRepository: UserDataRepository
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
