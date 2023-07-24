package com.zetung.zetpass.ui.auth

import androidx.lifecycle.ViewModel
import com.zetung.zetpass.utils.UserEnterAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userEnterAPI: UserEnterAPI): ViewModel() {


}