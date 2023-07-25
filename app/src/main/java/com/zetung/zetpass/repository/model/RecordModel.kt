package com.zetung.zetpass.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record")
data class RecordModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "owner")
    var owner:String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "data")
    var data: String,
    @ColumnInfo(name = "description")
    var description:String
)