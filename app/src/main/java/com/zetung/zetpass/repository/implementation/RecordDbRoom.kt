package com.zetung.zetpass.repository.implementation

import android.content.Context
import com.zetung.zetpass.repository.RecordDbAPI
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.repository.support.LocalDb

class RecordDbRoom (private val context: Context) : RecordDbAPI {
    override suspend fun insertRecord(recordModel: RecordModel) {
        LocalDb.getDb(context).getRecordDAO().insertRecord(recordModel)
    }

    override suspend fun selectRecords(owner: String): List<RecordModel> {
        return LocalDb.getDb(context).getRecordDAO().selectRecords(owner)
    }

    override suspend fun deleteRecord(recordModel: RecordModel) {
        LocalDb.getDb(context).getRecordDAO().deleteRecord(recordModel)
    }

    override suspend fun updateRecord(recordModel: RecordModel) {
        LocalDb.getDb(context).getRecordDAO().deleteRecord(recordModel)
    }
}