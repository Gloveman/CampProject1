package com.example.androidtest

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.properties.Delegates

class memolistadapter(private val context: Context?):RecyclerView.Adapter<memolistadapter.ViewHolder>() {
    var datas= mutableListOf<MemoData>()
    lateinit var lastitem:View
    var selecteddata=MemoData("NULL","NULL")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): memolistadapter.ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.memoitem,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=datas.size

    override fun onBindViewHolder(holder: memolistadapter.ViewHolder, position: Int) {
        holder.bind(datas[position])

    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val title: TextView =itemView.findViewById(R.id.txttitle)
        val memo:TextView=itemView.findViewById(R.id.txtmemo)
        fun bind(data:MemoData) {
            title.text=data.title
            memo.text=data.memo
            itemView.setOnClickListener {
                if(::lastitem.isInitialized&& lastitem == it&&selecteddata!=MemoData("NULL","NULL"))
                {
                    lastitem.setBackgroundColor(Color.WHITE)
                    selecteddata=MemoData("NULL","NULL")
                }
                else {
                    if (::lastitem.isInitialized) {
                        lastitem.setBackgroundColor(Color.WHITE)
                    }

                    it.setBackgroundColor(Color.GRAY)
                    selecteddata = data
                    lastitem = it
                }
            }

        }

    }

}

data class MemoData(
    val title:String,
    val memo:String
)