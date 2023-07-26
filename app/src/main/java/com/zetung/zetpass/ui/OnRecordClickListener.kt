package com.zetung.zetpass.ui

import com.zetung.zetpass.repository.model.RecordModel

interface OnRecordClickListener {
    fun onRecordClick(position: Int, data: RecordModel)
}