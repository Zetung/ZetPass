package com.zetung.zetpass.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zetung.zetpass.R
import com.zetung.zetpass.repository.model.RecordModel
import com.zetung.zetpass.ui.OnRecordClickListener

class HomeAdapter (var records: MutableList<RecordModel>) : RecyclerView.Adapter<DefaultHolder>(){

    private lateinit var likeClickListener: OnRecordClickListener
    private val VIEW_TYPE_STANDARD = 0
    private val VIEW_TYPE_NOTE = 1
    fun setOnButtonClickListener(listener: OnRecordClickListener) {
        likeClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return when (records[position].type) {
            "standard" -> VIEW_TYPE_STANDARD
            "note" -> VIEW_TYPE_NOTE
            else -> super.getItemViewType(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultHolder {
        return when (viewType) {
            VIEW_TYPE_STANDARD -> StandardHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.standart_item, parent, false)
            )

            VIEW_TYPE_NOTE -> NoteHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_item, parent, false)
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: DefaultHolder, position: Int) {
        if(holder.adapterPosition != RecyclerView.NO_POSITION){
            val data = records[holder.adapterPosition]
            holder.bind(data,likeClickListener)
        }
    }

}