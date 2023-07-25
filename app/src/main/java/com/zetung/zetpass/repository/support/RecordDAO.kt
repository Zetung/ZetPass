package com.zetung.zetpass.repository.support

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.zetung.zetpass.repository.model.RecordModel

@Dao
interface RecordDAO {
    @Insert
    fun insertRecord(recordModel: RecordModel)
    @Query("SELECT * FROM record WHERE owner=:owner")
    fun selectRecords(owner:String): List<RecordModel>
    @Delete
    fun deleteRecord(recordModel: RecordModel)
    @Update
    fun updateRecord(recordModel: RecordModel)
}