package com.example.androidtest

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class listadapter(private val context: Context?):RecyclerView.Adapter<listadapter.ViewHolder>(){
    var datas= mutableListOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.viewitem,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text=datas[position]
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val content: TextView =itemView.findViewById(R.id.txtname)
    }
}