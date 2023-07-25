package com.zetung.zetpass.repository.implementation

import android.content.Context
import android.content.SharedPreferences
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.model.AppSettingsModel
import javax.inject.Inject

class AppSettingsDbShared @Inject constructor (context: Context) : AppSettingsDbAPI {
    private val sharedPreferences : SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("settings_pref", Context.MODE_PRIVATE)
    }
    override fun getAppSettings(): AppSettingsModel {
        val localStorage = sharedPreferences.all as MutableMap<String,String>
        val ip = if(localStorage["ip"]!=null) localStorage["ip"].toString() else "0.0.0.0"
        val port = if(localStorage["port"]!=null) localStorage["port"]!!.toInt() else 0
        val online = if(localStorage["online"]!=null) localStorage["online"].toBoolean() else false
        return AppSettingsModel(ip,port,online)
    }

    override fun setAppSettings(appSettingsModel: AppSettingsModel) {
        val editor = sharedPreferences.edit()
        editor.putString("ip", appSettingsModel.ip)
        editor.putString("port", appSettingsModel.port.toString())
        editor.putString("online", appSettingsModel.online.toString())
        editor.apply()
    }
}