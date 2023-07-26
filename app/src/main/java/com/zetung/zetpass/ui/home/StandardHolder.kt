package com.zetung.zetpass.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zetung.zetpass.R
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener
import com.zetung.zetpass.ui.ParserView

class StandardHolder (itemView: View) : DefaultHolder(itemView) {
    private val nameText = itemView.findViewById<TextView>(R.id.name_item)
    private val loginText = itemView.findViewById<TextView>(R.id.login_item)
    private val parser = ParserView()

    override fun bind(data: RecordModel, listener: OnRecordClickListener){
        val viewData = parser.parseStringToMap(data.data,'!',';')
        nameText.text = data.name
        loginText.text = viewData["login"]
        itemView.setOnClickListener {
            listener.onRecordClick(adapterPosition, data)
        }
    }
}