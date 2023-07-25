package com.zetung.zetpass.repository

import com.zetung.zetpass.repository.model.RecordModel

interface RecordDbAPI {
    fun insertRecord(recordModel: RecordModel)
    fun selectRecords(owner:String): List<RecordModel>
    fun deleteRecord(recordModel: RecordModel)
    fun updateRecord(recordModel: RecordModel)
}