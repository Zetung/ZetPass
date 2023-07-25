package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserEnterAPI {
    fun enterUser(login: String, password:String): Flow<AuthState>
    fun regUser(login: String, password:String): Flow<AuthState>
    fun confirmUser(userModel: UserModel): AuthState
}