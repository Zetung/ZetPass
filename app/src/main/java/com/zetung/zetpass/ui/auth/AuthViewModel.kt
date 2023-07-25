package com.zetung.zetpass.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.model.UserModel
import com.zetung.zetpass.utils.AuthState
import com.zetung.zetpass.utils.UserEnterAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userEnterAPI: UserEnterAPI): ViewModel() {

    val msgState = MutableLiveData<AuthState>().apply {
        value = AuthState.NotStarted()
    }

    fun enter(login:String, password:String){
        CoroutineScope(Dispatchers.Main).launch {
            msgState.value = userEnterAPI.enterUser(login,password).last()
        }
    }

    fun reg(login:String, password:String){
        CoroutineScope(Dispatchers.Main).launch {
            msgState.value = userEnterAPI.regUser(login,password).last()
        }
    }

}