package com.zetung.zetpass.repository.support

interface DbCallback {
    fun onSuccess()
    fun onException(exception: Exception)
}