package com.zetung.zetpass.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.utils.LoadState
import com.zetung.zetpass.utils.ZetPassAPI
import com.zetung.zetpass.utils.singleton.CurrentRecords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val zetPassAPI: ZetPassAPI,
    private val currentRecords: CurrentRecords
): ViewModel() {

    val msgState = MutableLiveData<LoadState>()

    var records = MutableLiveData<MutableList<RecordModel>>().apply{
        CoroutineScope(Dispatchers.Main).launch{
            val result = withContext(Dispatchers.IO) {
                zetPassAPI.setupLocalRecords().last()
            }
            msgState.value = result
            if(msgState.value is LoadState.Done){
                value = zetPassAPI.getRecords().toMutableList()
                currentRecords.allRecords = zetPassAPI.getRecords().toMutableList()
            }
        }
    }

    fun setupLocalRecords(){
        records.value = currentRecords.allRecords
    }


    fun setRedactRecord(recordModel: RecordModel){
        currentRecords.redactRecord = recordModel
    }

}