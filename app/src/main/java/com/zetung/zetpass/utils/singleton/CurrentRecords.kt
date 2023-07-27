package com.zetung.zetpass.utils.singleton

import com.zetung.zetpass.repository.model.RecordModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentRecords @Inject constructor() {
    var allRecords = mutableListOf<RecordModel>()
}