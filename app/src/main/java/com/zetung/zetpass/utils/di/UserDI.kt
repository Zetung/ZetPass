package com.zetung.zetpass.utils.di

import android.content.Context
import com.zetung.zetpass.repository.AppSettingsDbAPI
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.implementation.UserDbRoom
import com.zetung.zetpass.utils.UserAPI
import com.zetung.zetpass.utils.UserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserDI {

    @Provides
    fun provideUserImpl(userDbAPI: UserDbAPI, appSettingsDbAPI: AppSettingsDbAPI): UserAPI {
        return UserImpl (userDbAPI,appSettingsDbAPI)
    }


    @Provides
    fun provideUserDbImpl(userDbRoom: UserDbRoom):UserDbAPI{
        return userDbRoom
    }

    @Provides
    fun provideUserDbRoom(@ApplicationContext context: Context):UserDbRoom{
        return UserDbRoom(context)
    }

}