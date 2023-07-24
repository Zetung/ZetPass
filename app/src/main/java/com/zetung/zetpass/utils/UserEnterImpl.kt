package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.model.UserModel
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import com.zetung.zetpass.utils.singleton.CurrentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import java.sql.SQLException
import javax.inject.Inject

class UserEnterImpl @Inject constructor(
    private val userDbAPI: UserDbAPI,
    private val currentAppSettings: CurrentAppSettings,
    private val currentUser: CurrentUser
) : UserEnterAPI {

    private var authState: AuthState = AuthState.NotStarted()

    private fun fetchDataFromDatabase(userModel: UserModel): Flow<UserModel> = flow {
        try {
            val localUser = userDbAPI.getUser(userModel.login)
            if(localUser.login.isNotEmpty()){
                emit(localUser)
            } else {
                authState = AuthState.Error("Not found")
                emit(userModel)
            }
        } catch (e: SQLException){
            authState = AuthState.Error(e.toString())
        }
    }

    private fun enterProcess(userModel: UserModel): Flow<AuthState> = flow{
        authState = AuthState.Loading()
        emit(authState)
        val deferredDatabase = CoroutineScope(Dispatchers.Main).async { fetchDataFromDatabase(userModel) }
        val fromDatabase = deferredDatabase.await()
        if(authState is AuthState.Loading)
            if (currentAppSettings.appSettingsModel.online){
                if (fromDatabase.last().confirm){

                } else {

                }
            } else {
                currentUser.userModel = fromDatabase.last()
                authState = AuthState.Done("Local load is done!")
            }
        emit(authState)
    }

    override fun enterUser(userModel: UserModel): Flow<AuthState> {
        return enterProcess(userModel)
    }

    private fun regProcess(userModel: UserModel): Flow<AuthState> = flow{
        authState = AuthState.Loading()
        if (currentAppSettings.appSettingsModel.online){

        } else {

            currentUser.userModel = fromDatabase.last()
            authState = AuthState.Done("Local load is done!")
        }
    }

    override fun regUser(userModel: UserModel): Flow<AuthState> {
        return regProcess(userModel)
    }

    override fun confirmUser(userModel: UserModel): AuthState {
        TODO("Not yet implemented")
    }


}