package com.zetung.zetpass.repository

import com.zetung.zetpass.repository.model.UserModel

interface UserDbAPI {
    fun addUser(user: UserModel)
    fun updateUserStatus(login: String)
    suspend fun getUser(login: String):UserModel
}