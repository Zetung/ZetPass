package com.zetung.zetpass.utils.di

import android.content.Context
import com.zetung.zetpass.repository.RecordDbAPI
import com.zetung.zetpass.repository.implementation.RecordDbRoom
import com.zetung.zetpass.utils.ZetPassAPI
import com.zetung.zetpass.utils.ZetPassImpl
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import com.zetung.zetpass.utils.singleton.CurrentRecords
import com.zetung.zetpass.utils.singleton.CurrentUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RecordDI {

    @Provides
    fun provideZetPassImpl(currentAppSettings: CurrentAppSettings,
                           currentRecords: CurrentRecords,
                           currentUser: CurrentUser,
                           recordDbAPI: RecordDbAPI): ZetPassAPI {
        return ZetPassImpl(currentAppSettings,currentUser,currentRecords,recordDbAPI)
    }

    @Provides
    fun provideRecordDbImpl(recordDbRoom: RecordDbRoom):RecordDbAPI{
        return recordDbRoom
    }

    @Provides
    fun provideRecordRoom(@ApplicationContext context: Context):RecordDbRoom{
        return RecordDbRoom(context)
    }
}