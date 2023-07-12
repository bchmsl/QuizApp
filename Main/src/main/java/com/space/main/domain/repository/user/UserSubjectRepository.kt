package com.space.main.domain.repository.user

import com.space.main.data.local.database.model.user.UserSubjectEntity
import com.space.main.domain.model.user.UserSubjectDomainModel

abstract class UserSubjectRepository {
    abstract suspend fun retrieveUserSubjects(username: String): List<UserSubjectDomainModel>
    abstract suspend fun updateOrInsertUserSubject(
        userSubjectEntity: UserSubjectEntity
    )

    abstract suspend fun getUserSubject(username: String, quizTitle: String): UserSubjectEntity?
    abstract suspend fun saveUserPoints(username: String, quizTitle: String, points: Int)
}
