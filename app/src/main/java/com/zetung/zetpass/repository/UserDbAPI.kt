package com.zetung.zetpass.repository

import com.zetung.zetpass.repository.model.UserModel
import com.zetung.zetpass.repository.support.DbCallback

interface UserDbAPI {
    suspend fun addUser(user: UserModel)
    suspend fun addUser(user: UserModel,dbCallback: DbCallback)
    suspend fun updateUserStatus(login: String)
    suspend fun getUser(login: String):UserModel
}