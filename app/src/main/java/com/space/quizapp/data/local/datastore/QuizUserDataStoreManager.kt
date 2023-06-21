package com.space.quizapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class QuizUserDataStoreManager(private val context: Context) :
    QuizDataStoreManager<String> {
    private val Context.dataStore by preferencesDataStore("user_preferences")
    protected abstract val key: String

    override suspend fun saveValue(value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override fun readValue(): Flow<String> {
        val preferences = context.dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: EMPTY_STRING
        }
        return preferences
    }

    companion object {
        const val EMPTY_STRING = ""
    }
}
