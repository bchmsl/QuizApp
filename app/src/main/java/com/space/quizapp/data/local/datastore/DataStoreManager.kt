package com.space.quizapp.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager<T : Any> {

    suspend fun saveValue(value: T)
    fun readValue(): Flow<T>
}
