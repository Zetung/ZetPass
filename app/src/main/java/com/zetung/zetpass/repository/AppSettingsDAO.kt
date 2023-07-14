package com.zetung.zetpass.repository

import com.zetung.zetpass.repository.model.AppSettingsModel

interface AppSettingsDAO {
    fun getAppSettings(): AppSettingsModel
    fun setAppSettings(appSettingsModel: AppSettingsModel)
    fun getIp():String
    fun getPort():Int
    fun getOnline():Boolean
}