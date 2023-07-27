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
import java.sql.SQLException
import javax.inject.Inject

class UserEnterImpl @Inject constructor(
    private val userDbAPI: UserDbAPI,
    private val currentAppSettings: CurrentAppSettings,
    private val currentUser: CurrentUser
) : UserEnterAPI {

    private var loadState: LoadState = LoadState.NotStarted()


    //----------Sign up block----------
    private fun fetchEnterFromDatabase(login: String): Flow<LoadState> = flow {
        try {
            val localUser = userDbAPI.getUser(login)
            if(localUser != null){
                currentUser.userModel = localUser
                loadState = LoadState.Loading()
                emit(loadState)
            } else {
                loadState = LoadState.Error("Not found")
                emit(loadState)
            }
        } catch (e: SQLException){
            loadState = LoadState.Error(e.toString())
            emit(loadState)
        }
    }

    private fun enterProcess(login: String, password:String): Flow<LoadState> = flow{
        val deferredDatabase = CoroutineScope(Dispatchers.Main).async { fetchEnterFromDatabase(login) }
        val fromDatabase = deferredDatabase.await()
        if(fromDatabase.last() is LoadState.Loading)
            if (currentAppSettings.appSettingsModel.online){
                if (currentUser.userModel.confirm){

                } else {
                    // to server
                }
            } else {
                loadState = if (currentUser.userModel.password == password)
                    LoadState.Done("Local enter is done!")
                else
                    LoadState.Error("Wrong password")
            }
        emit(loadState)
    }

    override fun enterUser(login: String, password:String): Flow<LoadState> {
        loadState = LoadState.NotStarted()
        return enterProcess(login,password)
    }

    //----------Sign in block----------
    private fun fetchRegFromDatabase(userModel: UserModel): Flow<LoadState> = flow {
        userDbAPI.addUser(userModel,object : DbCallback{
            override fun onSuccess() {
                loadState = LoadState.Loading()
            }
            override fun onException(exception: SQLException) {
                loadState = LoadState.Error(exception.toString())
            }
        })
        emit(loadState)
    }

    private fun regProcess(login: String, password:String): Flow<LoadState> = flow{

        val userModel = UserModel(null,login,password,currentAppSettings.appSettingsModel.online)

        if (currentAppSettings.appSettingsModel.online){

        } else {
            val deferredDatabase = CoroutineScope(Dispatchers.Main).async { fetchRegFromDatabase(userModel) }
            val fromDatabase = deferredDatabase.await()
            if (fromDatabase.last() is LoadState.Loading){
                currentUser.userModel = userModel
                loadState = LoadState.Done("Local reg is done!")
            }
        }
        emit(loadState)
    }

    override fun regUser(login: String, password:String): Flow<LoadState> {
        loadState = LoadState.NotStarted()
        return regProcess(login,password)
    }

    //----------Confirm block----------
    override fun confirmUser(userModel: UserModel): LoadState {
        TODO("Not yet implemented")
    }


}