package com.space.main.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.space.common.util.Constants.EMPTY_STRING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreManager(private val context: Context) :
    DataStoreManager<String> {
    private val Context.dataStore by preferencesDataStore("user_preferences")
    private val key: String = KEY

    override suspend fun saveValue(value: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun readValue(): Flow<String> {
        val preferences = context.dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: EMPTY_STRING
        }
        return preferences
    }

    companion object {
        private const val KEY = "user_token"
    }
}
