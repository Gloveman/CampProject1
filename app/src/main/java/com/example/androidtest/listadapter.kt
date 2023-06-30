package com.example.androidtest

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class listadapter(private val context: Context?):RecyclerView.Adapter<listadapter.ViewHolder>(){
    var datas= mutableListOf<ContactData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.viewitem,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=datas[position].name
        holder.number.text=datas[position].number
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView =itemView.findViewById(R.id.txtname)
        val number:TextView=itemView.findViewById(R.id.txtnum)
    }
}

data class ContactData(
    //val img:Int,
    val name:String,
    val number:String
)