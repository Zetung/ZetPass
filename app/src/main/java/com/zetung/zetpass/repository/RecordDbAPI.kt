package com.zetung.zetpass.repository

import com.zetung.zetpass.repository.model.RecordModel

interface RecordDbAPI {
    suspend fun insertRecord(recordModel: RecordModel)
    suspend fun selectRecords(owner:String): List<RecordModel>
    suspend fun deleteRecord(recordModel: RecordModel)
    suspend fun updateRecord(recordModel: RecordModel)
}