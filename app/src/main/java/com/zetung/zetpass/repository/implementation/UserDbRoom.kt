package com.zetung.zetpass.repository.implementation

import android.content.Context
import com.zetung.zetpass.repository.LocalDb
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDbRoom (private val context: Context) : UserDbAPI {

    override fun addUser(user: UserModel) {
        CoroutineScope(Dispatchers.IO).launch {
            LocalDb.getDb(context).getUserDAO().newUser(user)
        }
    }

    override suspend fun getUser(login: String): UserModel {
        return LocalDb.getDb(context).getUserDAO().selectUser(login)
    }

    override fun updateUserStatus(login: String) {
        CoroutineScope(Dispatchers.IO).launch {
            LocalDb.getDb(context).getUserDAO().updateStatus(login)
        }
    }
}