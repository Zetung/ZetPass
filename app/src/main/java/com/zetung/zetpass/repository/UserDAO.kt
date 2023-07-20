package com.zetung.zetpass.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zetung.zetpass.repository.model.UserModel


@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun newUser(userModel: UserModel)
    @Query("SELECT EXISTS(SELECT * FROM user WHERE login=:login)")
    fun selectUser(login:String):UserModel
    @Query("UPDATE user SET confirm=1 WHERE login=:login")
    fun updateStatus(login: String)

}