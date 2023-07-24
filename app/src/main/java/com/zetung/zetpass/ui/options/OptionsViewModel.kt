package com.zetung.zetpass.ui.options

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.model.AppSettingsModel
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val appSettingsDbAPI: AppSettingsDbAPI,
    private val currentAppSettings: CurrentAppSettings): ViewModel() {

    val ip = MutableLiveData<String>().apply {
        value = currentAppSettings.appSettingsModel.ip
    }

    val port = MutableLiveData<Int>().apply {
        value = currentAppSettings.appSettingsModel.port
    }

    val online = MutableLiveData<Boolean>().apply {
        value = currentAppSettings.appSettingsModel.online
    }

    fun saveSettings(){
        val ipSave = if (ip.value != null) ip.value else "0.0.0.0"
        val portSave = if (port.value != null) port.value else 0
        val onlineSave = if (online.value != null) online.value else false
        currentAppSettings.appSettingsModel.ip = ipSave!!
        currentAppSettings.appSettingsModel.port = portSave!!
        currentAppSettings.appSettingsModel.online = onlineSave!!
        appSettingsDbAPI.setAppSettings(currentAppSettings.appSettingsModel)
    }

}