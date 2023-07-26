package com.zetung.zetpass.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zetung.zetpass.R
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener

class NoteHolder (itemView: View) : DefaultHolder(itemView) {
    private val imageView = itemView.findViewById<ImageView>(R.id.type_item)
    private val nameText = itemView.findViewById<TextView>(R.id.name_item)

    override fun bind(data: RecordModel, listener: OnRecordClickListener){
        nameText.text = data.name

        itemView.setOnClickListener {
            listener.onRecordClick(adapterPosition, data)
        }
    }
}