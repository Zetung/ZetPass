package com.zetung.zetpass.ui.auth

import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.utils.UserAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userAPI: UserAPI): ViewModel() {


}