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
    override fun loadUser(): AuthState {
        TODO("Not yet implemented")
    }

    override fun enterUser(): AuthState {
        TODO("Not yet implemented")
    }

    override fun regUser(): AuthState {
        TODO("Not yet implemented")
    }

    override fun confirmUser(): AuthState {
        TODO("Not yet implemented")
    }


}