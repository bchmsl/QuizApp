package com.space.quizapp.data.local.datastore

import android.content.Context

class QuizUserDataStoreManagerImpl(context: Context) : QuizUserDataStoreManager(context) {
    override val key: String
        get() = KEY

    companion object {
        private const val KEY = "user_token"
    }
}
