package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserEnterAPI {
    fun enterUser(userModel: UserModel): Flow<AuthState>
    fun regUser(userModel: UserModel): Flow<AuthState>
    fun confirmUser(userModel: UserModel): AuthState
}