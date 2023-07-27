package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.model.RecordModel
import kotlinx.coroutines.flow.Flow

interface ZetPassAPI {
    fun setupLocalRecords(): Flow<LoadState>
    fun addRecord(recordModel: RecordModel): Flow<LoadState>
    fun deleteRecord(recordModel: RecordModel):LoadState
    fun redactRecord(recordModel: RecordModel):LoadState
    fun saveToServer():LoadState
    fun loadFromServer():LoadState
    fun getRecords():List<RecordModel>
//    fun getRedactRecord():RecordModel
//    fun setRedactRecord(recordModel: RecordModel)
//    fun setRedactState(redactState: RedactState)
//    fun getRedactState():RedactState
}