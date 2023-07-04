package com.example.androidtest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class groupviewadapter(val context: Context?):RecyclerView.Adapter<groupviewadapter.ViewHolder>() {
    var members= mutableListOf<ContactData>()
    private lateinit var listener:GroupListener
    lateinit var lastitem:View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.viewitem,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=members.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(context!=null){
            Glide.with(context)
                .load(members[position].imageResId)
                .into(holder.itemView.findViewById(R.id.contactimg))
        }
        holder.bind(members[position])

    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val name: TextView =itemView.findViewById(R.id.txtname)
        val number: TextView =itemView.findViewById(R.id.txtnum)
        fun bind(person:ContactData){
            name.text=person.name
            number.text=person.number
            itemView.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("무엇을 할까요?")
                    .setItems(arrayOf("전화 걸기","문자 보내기","그룹에서 삭제하기")){ dialog, which ->
                        when(which)
                        {
                            0->{
                                val callIntent = Intent(Intent.ACTION_CALL);
                                callIntent.data = Uri.parse("tel:${number.text}")
                                context?.startActivity(callIntent, null)
                            }
                            1->{
                                val messageIntent= Intent(Intent.ACTION_VIEW)
                                messageIntent.data= Uri.parse("smsto:${number.text}")
                                context?.startActivity(messageIntent,null)
                            }
                            2->{
                                //fragment에서 처리
                                listener.DeleteHandler(person)
                            }
                        }

                    }
                    .create()
                    .show()

            }
        }
    }

    fun setDeleteListener(func:(ContactData)->Unit) {
        this.listener = object : GroupListener {

            override fun DeleteHandler(person: ContactData) {
                func(person)
            }
        }
    }
}

interface  GroupListener{
    fun DeleteHandler(person:ContactData)
}