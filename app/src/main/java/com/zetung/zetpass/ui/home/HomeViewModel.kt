package com.zetung.zetpass.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.utils.LoadState
import com.zetung.zetpass.utils.UserEnterAPI
import com.zetung.zetpass.utils.ZetPassAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val zetPassAPI: ZetPassAPI
): ViewModel() {

    val msgState = MutableLiveData<LoadState>()

    var records = MutableLiveData<MutableList<RecordModel>>()

    fun setupLocalRecords(){
        CoroutineScope(Dispatchers.Main).launch{
            val result = withContext(Dispatchers.IO) {
                zetPassAPI.setupLocalRecords().last()
            }
            msgState.value = result
            if(msgState.value is LoadState.Done)
                records.value = zetPassAPI.getRecords().toMutableList()
        }
    }

    fun addLocalRecord(recordModel: RecordModel){
        CoroutineScope(Dispatchers.Main).launch{
            val result = withContext(Dispatchers.IO) {
                zetPassAPI.addRecord(recordModel).last()
            }
            msgState.value = result
            if(msgState.value is LoadState.Done)
                records.value = zetPassAPI.getRecords().toMutableList()
        }
    }

}