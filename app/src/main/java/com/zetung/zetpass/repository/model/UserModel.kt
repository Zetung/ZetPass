package com.zetung.zetpass.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user", indices = [Index(value = ["login"],
    unique = true)])
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "login")
    var login:String,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name = "confirm")
    var confirm:Boolean,
)
