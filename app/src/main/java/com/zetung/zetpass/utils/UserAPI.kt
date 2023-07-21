package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.model.UserModel

interface UserAPI {
    fun getState(): AuthState
    fun loadUser(): UserModel
    fun enterUser(): Boolean
    fun regUser(): Boolean
    fun confirmUser(): Boolean
}