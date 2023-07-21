package com.zetung.zetpass.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class CurrentUser @Inject constructor (
    var login: String = "",
    var password: String = "",
    var confirm: Boolean = false,
    var isAuth: Boolean = false
)