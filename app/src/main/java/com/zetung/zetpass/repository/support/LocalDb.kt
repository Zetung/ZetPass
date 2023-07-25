package com.zetung.zetpass.repository.support

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zetung.zetpass.repository.model.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class LocalDb: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getRecordDAO(): RecordDAO
    companion object{
        fun getDb(context: Context): LocalDb {
            return Room.databaseBuilder(
                context.applicationContext,
                LocalDb::class.java,
                "ZetPass.db"
            ).build()
        }
    }
}