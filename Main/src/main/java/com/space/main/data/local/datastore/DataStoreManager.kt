package com.space.main.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager<T : Any> {
    suspend fun saveValue(value: T)
    suspend fun readValue(): Flow<T>
}
