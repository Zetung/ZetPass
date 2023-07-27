package com.zetung.zetpass.utils

sealed class LoadState{
    abstract var msg: String
    class NotStarted(override var msg: String = "Not started") : LoadState()
    class Loading(override var msg: String = "Loading...") : LoadState()
    class Done(override var msg: String = "Done!") : LoadState()
    class Error(override var msg: String = "Error") : LoadState()
}
