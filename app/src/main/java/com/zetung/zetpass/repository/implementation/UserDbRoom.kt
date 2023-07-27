package com.zetung.zetpass.repository.implementation

import android.content.Context
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.model.UserModel
import com.zetung.zetpass.repository.support.DbCallback
import com.zetung.zetpass.repository.support.LocalDb
import java.sql.SQLException

class UserDbRoom (private val context: Context) : UserDbAPI {

    override suspend fun addUser(user: UserModel) {
        LocalDb.getDb(context).getUserDAO().newUser(user)
    }

    override suspend fun addUser(user: UserModel, dbCallback: DbCallback) {
        try{
            LocalDb.getDb(context).getUserDAO().newUser(user)
            dbCallback.onSuccess()
        } catch (ex: SQLException){
            dbCallback.onException(ex)
        }
    }


    override suspend fun getUser(login: String): UserModel {
        return LocalDb.getDb(context).getUserDAO().selectUser(login)
    }

    override suspend fun updateUserStatus(login: String) {
        LocalDb.getDb(context).getUserDAO().updateStatus(login)
    }
}