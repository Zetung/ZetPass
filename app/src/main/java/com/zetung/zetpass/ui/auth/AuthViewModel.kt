package com.zetung.zetpass.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.utils.LoadState
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

    val msgState = MutableLiveData<LoadState>()

    fun enter(login:String, password:String){
        if (checkFields(login, password))
            CoroutineScope(Dispatchers.Main).launch {
                msgState.value = userEnterAPI.enterUser(login,password).last()
            }
    }

    fun reg(login:String, password:String){
        if (checkFields(login, password))
            CoroutineScope(Dispatchers.Main).launch {
                msgState.value = userEnterAPI.regUser(login,password).last()
            }
    }

    private fun checkFields(login: String, password: String):Boolean{
        when {
            login.isEmpty() || password.isEmpty() -> {
                msgState.value = LoadState.Error("Fields is empty!")
                return false
            }
            "%" in login || "%" in password -> {
                msgState.value = LoadState.Error("You can't use '%'")
                return false
            }
        }
        return true
    }

}