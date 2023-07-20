package com.zetung.zetpass.ui.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zetung.zetpass.repository.implementation.AppSettingsDbShared
import com.zetung.zetpass.repository.model.AppSettingsModel

class OptionsViewModel(application: Application): AndroidViewModel(application) {

    private val appSettingsDAO = AppSettingsDbShared(application)

    val ip = MutableLiveData<String>().apply {
        value = appSettingsDAO.getIp()
    }

    val port = MutableLiveData<Int>().apply {
        value = appSettingsDAO.getPort()
    }

    val online = MutableLiveData<Boolean>().apply {
        value = appSettingsDAO.getOnline()
    }

    override fun onCleared() {
        saveSettings()
        super.onCleared()
    }

    fun saveSettings(){
        val ipSave = if (ip.value != null) ip.value else "0.0.0.0"
        val portSave = if (port.value != null) port.value else 0
        val onlineSave = if (online.value != null) online.value else false
        appSettingsDAO.setAppSettings(AppSettingsModel(ipSave!!,portSave!!,onlineSave!!))
    }

}