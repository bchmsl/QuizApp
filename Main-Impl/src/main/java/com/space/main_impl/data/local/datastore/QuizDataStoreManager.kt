package com.space.main_impl.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface QuizDataStoreManager<T : Any> {
    suspend fun saveValue(value: T)
    suspend fun readValue(): Flow<T>
}
