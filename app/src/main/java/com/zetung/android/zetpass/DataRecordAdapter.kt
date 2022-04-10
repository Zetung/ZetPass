package com.zetung.android.zetpass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataRecordAdapter(private val dataRecords: ArrayList<DataRecord>) :
    RecyclerView.Adapter<DataRecordAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appView: TextView = itemView.findViewById(R.id.app)
        val loginView: TextView = itemView.findViewById(R.id.login)
        val passView: TextView = itemView.findViewById(R.id.pass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataRecord = dataRecords[position]
        holder.appView.text = dataRecord.app
        holder.loginView.text = dataRecord.login
        holder.passView.text = dataRecord.pass
    }

    override fun getItemCount(): Int {
        return dataRecords.size
    }
}