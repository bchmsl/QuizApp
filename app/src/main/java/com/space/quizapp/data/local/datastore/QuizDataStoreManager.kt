package com.space.quizapp.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface QuizDataStoreManager<T : Any> {

    suspend fun saveValue(value: T)
    fun readValue(): Flow<T>
}