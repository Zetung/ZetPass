package com.zetung.zetpass.repository.support

import java.lang.Exception

interface DbCallback {
    fun onSuccess()
    fun onException(exception: Exception)
}