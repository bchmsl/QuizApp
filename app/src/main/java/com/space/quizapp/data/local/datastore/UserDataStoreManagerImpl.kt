package com.space.quizapp.data.local.datastore

import android.content.Context

class UserDataStoreManagerImpl(context: Context) : UserDataStoreManager(context) {
    override val key: String
        get() = KEY

    companion object {
        private const val KEY = "user_token"
    }
}
