package com.zetung.zetpass.utils.di

import android.content.Context
import com.zetung.zetpass.repository.UserDbAPI
import com.zetung.zetpass.repository.implementation.UserDbRoom
import com.zetung.zetpass.utils.UserEnterAPI
import com.zetung.zetpass.utils.UserEnterImpl
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import com.zetung.zetpass.utils.singleton.CurrentUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object UserDI {

    @Provides
    fun provideUserImpl(userDbAPI: UserDbAPI,
                        currentAppSettings: CurrentAppSettings,
                        currentUser: CurrentUser): UserEnterAPI {
        return UserEnterImpl (userDbAPI,currentAppSettings,currentUser)
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