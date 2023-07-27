package com.zetung.zetpass.utils

import com.zetung.zetpass.repository.RecordDbAPI
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.utils.singleton.CurrentAppSettings
import com.zetung.zetpass.utils.singleton.CurrentRecords
import com.zetung.zetpass.utils.singleton.CurrentUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.SQLException
import javax.inject.Inject

class ZetPassImpl @Inject constructor (
    private val currentAppSettings: CurrentAppSettings,
    private val currentUser: CurrentUser,
    private val recordDbAPI: RecordDbAPI
) : ZetPassAPI {

    private var loadState: LoadState = LoadState.NotStarted()
    private var currentRecords = mutableListOf<RecordModel>()

    override fun setupLocalRecords(): Flow<LoadState> = flow{
        loadState = LoadState.Loading()
        try {
            currentRecords = recordDbAPI.selectRecords(currentUser.userModel.login).toMutableList()
            loadState = LoadState.Done()
            emit(loadState)
        } catch (e: SQLException){
            loadState = LoadState.Error(e.toString())
            emit(loadState)
        }
    }

    override fun addRecord(recordModel: RecordModel): Flow<LoadState> = flow {
        loadState = LoadState.Loading()
        try{
            recordDbAPI.insertRecord(recordModel)
            loadState = LoadState.Done()
            emit(loadState)
        } catch (e: SQLException){
            loadState = LoadState.Error(e.toString())
            emit(loadState)
        }
    }

    override fun deleteRecord(recordModel: RecordModel): LoadState {
        TODO("Not yet implemented")
    }

    override fun redactRecord(recordModel: RecordModel): LoadState {
        TODO("Not yet implemented")
    }

    override fun saveToServer(): LoadState {
        TODO("Not yet implemented")
    }

    override fun loadFromServer(): LoadState {
        TODO("Not yet implemented")
    }

    override fun getRecords(): List<RecordModel> {
        return currentRecords
    }
//
//    override fun getRedactRecord(): RecordModel {
//        return currentRecords.redactRecord
//    }
//
//    override fun setRedactRecord(recordModel: RecordModel) {
//        currentRecords.redactRecord = recordModel
//    }
//
//    override fun setRedactState(redactState: RedactState) {
//        this.redactState = redactState
//    }
//
//    override fun getRedactState(): RedactState {
//        return redactState
//    }


}