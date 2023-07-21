package com.zetung.zetpass.utils.di

import android.content.Context
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.implementation.AppSettingsDbShared
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppSettingsDI {

    @Provides
    fun provideAppSettingsImpl(appSettingsDbShared: AppSettingsDbShared): AppSettingsDbAPI{
        return appSettingsDbShared
    }

    @Provides
    fun provideAppSettingsShared(@ApplicationContext context: Context): AppSettingsDbShared{
        return AppSettingsDbShared(context)
    }


}