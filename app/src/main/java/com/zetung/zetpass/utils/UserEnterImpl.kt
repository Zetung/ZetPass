package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.model.UserModel
import com.zetung.zetpass.repository.support.DbCallback
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import com.zetung.zetpass.utils.singleton.CurrentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import java.lang.Exception
import java.sql.SQLException
import javax.inject.Inject

class UserEnterImpl @Inject constructor(
    private val userDbAPI: UserDbAPI,
    private val currentAppSettings: CurrentAppSettings,
    private val currentUser: CurrentUser
) : UserEnterAPI {

    private var authState: AuthState = AuthState.NotStarted()


    //----------Sign up block----------
    private fun fetchEnterFromDatabase(login: String): Flow<AuthState> = flow {
        try {
            val localUser = userDbAPI.getUser(login)
            if(localUser != null){
                currentUser.userModel = localUser
                authState = AuthState.Loading()
                emit(authState)
            } else {
                authState = AuthState.Error("Not found")
                emit(authState)
            }
        } catch (e: SQLException){
            authState = AuthState.Error(e.toString())
            emit(authState)
        }
    }

    private fun enterProcess(login: String, password:String): Flow<AuthState> = flow{
        val deferredDatabase = CoroutineScope(Dispatchers.Main).async { fetchEnterFromDatabase(login) }
        val fromDatabase = deferredDatabase.await()
        if(fromDatabase.last() is AuthState.Loading)
            if (currentAppSettings.appSettingsModel.online){
                if (currentUser.userModel.confirm){

                } else {

                }
            } else {
                if (currentUser.userModel.password == password)
                    authState = AuthState.Done("Local load is done!")
                else
                    authState = AuthState.Error("Wrong password")
            }
        emit(authState)
    }

    override fun enterUser(login: String, password:String): Flow<AuthState> {
        authState = AuthState.NotStarted()
        return enterProcess(login,password)
    }

    //----------Sign in block----------
    private fun fetchRegFromDatabase(userModel: UserModel): Flow<AuthState> = flow {
        userDbAPI.addUser(userModel,object : DbCallback{
            override fun onSuccess() {
                authState = AuthState.Loading()
            }
            override fun onException(exception: Exception) {
                authState = AuthState.Error(exception.message)
            }
        })
        emit(authState)
    }

    private fun regProcess(login: String, password:String): Flow<AuthState> = flow{

        val userModel = UserModel(null,login,password,currentAppSettings.appSettingsModel.online)

        if (currentAppSettings.appSettingsModel.online){

        } else {
            val deferredDatabase = CoroutineScope(Dispatchers.Main).async { fetchRegFromDatabase(userModel) }
            val fromDatabase = deferredDatabase.await()
            if (fromDatabase.last() is AuthState.Loading){
                currentUser.userModel = userModel
                authState = AuthState.Done("Local reg is done!")

            }
        }
        emit(authState)
    }

    override fun regUser(login: String, password:String): Flow<AuthState> {
        authState = AuthState.NotStarted()
        return regProcess(login,password)
    }

    //----------Confirm block----------
    override fun confirmUser(userModel: UserModel): AuthState {
        TODO("Not yet implemented")
    }


}