package com.zetung.zetpass.utils
interface UserAPI {
    fun loadUser(): AuthState
    fun enterUser(): AuthState
    fun regUser(): AuthState
    fun confirmUser(): AuthState
}