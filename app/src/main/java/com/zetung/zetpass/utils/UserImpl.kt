package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.model.UserModel
import javax.inject.Inject

class UserImpl @Inject constructor(
    private val userDbAPI: UserDbAPI,
    private val appSettingsDAO: AppSettingsDbAPI
) : UserAPI {

    private var authState: AuthState = AuthState.NotStarted()

    override fun getState(): AuthState {
        return authState
    }

    override fun loadUser(): UserModel {
        TODO("Not yet implemented")
    }

    override fun enterUser(): Boolean {
        TODO("Not yet implemented")
    }

    override fun regUser(): Boolean {
        TODO("Not yet implemented")
    }

    override fun confirmUser(): Boolean {
        TODO("Not yet implemented")
    }
}