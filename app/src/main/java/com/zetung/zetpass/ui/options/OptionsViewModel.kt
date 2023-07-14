package com.zetung.zetpass.ui.options

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.AppSettingsDAO
import com.zetung.zetpass.repository.AppSettingsShared

class OptionsViewModel(application: Application): AndroidViewModel(application) {

    private val appSettingsDAO = AppSettingsShared(application)

    private val _ip = MutableLiveData<String>().apply {
        value = appSettingsDAO.getIp()
    }
    val ip: LiveData<String> = _ip

    private val _port = MutableLiveData<Int>().apply {
        value = appSettingsDAO.getPort()
    }
    val port: LiveData<Int> = _port

    private val _online = MutableLiveData<Boolean>().apply {
        value = appSettingsDAO.getOnline()
    }
    val online: LiveData<Boolean> = _online

}