package com.zetung.zetpass.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener

abstract class DefaultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: RecordModel, listener: OnRecordClickListener)
}