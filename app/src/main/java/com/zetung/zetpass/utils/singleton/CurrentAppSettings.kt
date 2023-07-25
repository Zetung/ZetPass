package com.zetung.zetpass.utils.singleton

import com.zetung.zetpass.repository.model.AppSettingsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentAppSettings @Inject constructor (){
    var appSettingsModel = AppSettingsModel("0.0.0.0",0,false)
}