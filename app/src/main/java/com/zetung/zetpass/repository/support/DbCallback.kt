package com.zetung.zetpass.repository.support

import java.sql.SQLException

interface DbCallback {
    fun onSuccess()
    fun onException(exception: SQLException)
}