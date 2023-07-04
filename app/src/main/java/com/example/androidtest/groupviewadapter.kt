package com.example.androidtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class groupviewadapter(val context: Context?):RecyclerView.Adapter<groupviewadapter.ViewHolder>() {
    var members= mutableListOf<ContactData>()
    lateinit var lastitem:View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.memoitem,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=members.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(members[position])
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(person:ContactData){

        }
    }
}