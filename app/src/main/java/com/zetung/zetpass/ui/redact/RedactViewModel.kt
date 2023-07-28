package com.zetung.zetpass.ui.redact

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.utils.LoadState
import com.zetung.zetpass.utils.ZetPassAPI
import com.zetung.zetpass.utils.singleton.CurrentRecords
import com.zetung.zetpass.utils.singleton.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RedactViewModel @Inject constructor(
    private val zetPassAPI: ZetPassAPI,
    private val currentRecords: CurrentRecords,
    private val currentUser: CurrentUser
) : ViewModel() {

    var redactRecord = MutableLiveData<RecordModel>()

    fun loadRedactRecord(){
        redactRecord.value = currentRecords.redactRecord
    }

    fun addLocalRecord(type:String,name: String, data: String, description:String){
        val temp = RecordModel(null,currentUser.userModel.login,type,name,data,description)

        currentRecords.allRecords.add(temp)
        CoroutineScope(Dispatchers.Main).launch{
            val result = withContext(Dispatchers.IO) {
                zetPassAPI.addRecord(temp).last()
            }
        }
    }



}