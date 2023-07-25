package com.zetung.zetpass.repository.support

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zetung.zetpass.repository.model.UserModel


@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun newUser(userModel: UserModel)
    @Query("SELECT * FROM user WHERE login=:login")
    suspend fun selectUser(login:String):UserModel
    @Query("UPDATE user SET confirm=1 WHERE login=:login")
    suspend fun updateStatus(login: String)

}