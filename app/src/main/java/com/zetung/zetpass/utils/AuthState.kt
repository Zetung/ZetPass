package com.zetung.zetpass.utils

sealed class AuthState{
    abstract var msg: String
    class NotStarted(override var msg: String = "Not started") : AuthState()
    class Loading(override var msg: String = "Loading...") : AuthState()
    class Done(override var msg: String = "Done!") : AuthState()
    class Error(override var msg: String = "Error") : AuthState()
}
