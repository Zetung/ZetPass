package com.zetung.zetpass.utils

sealed class AuthState{
    abstract var msg: String
    class NotStarted(override var msg: String = "NS") : AuthState()
    class Loading(override var msg: String = "L") : AuthState()
    class Done(override var msg: String = "D") : AuthState()
    class Error(override var msg: String = "E") : AuthState()
}
