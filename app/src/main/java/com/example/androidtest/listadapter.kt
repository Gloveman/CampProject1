package com.example.androidtest

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.Gravity
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
        val name = datas[position].name
        val number = datas[position].number
        val imageResId = datas[position].imageResId
        holder.bind(datas[position])

    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name: TextView =itemView.findViewById(R.id.txtname)
        val number:TextView=itemView.findViewById(R.id.txtnum)
        fun bind(data:ContactData){
            name.text=data.name
            number.text=data.number
            itemView.setOnClickListener{
                AlertDialog.Builder(context)
                    .setTitle("무엇을 할까요?")
                   // .setMessage("${name.text}에게 전화를 걸까요?")
                    .setItems(arrayOf("전화 걸기","문자 보내기")){ dialog, which ->
                        if(which==0) {
                            val callIntent = Intent(Intent.ACTION_CALL);
                            callIntent.data = Uri.parse("tel:${number.text}")
                            context?.startActivity(callIntent, null)
                        }
                        else{
                            val messageIntent=Intent(Intent.ACTION_VIEW)
                            messageIntent.data=Uri.parse("smsto:${number.text}")
                            context?.startActivity(messageIntent,null)
                        }
                    }
                    .create()
                    .show()
            }
        }
    }
}

data class ContactData(
    val name:String,
    val number:String
    val imageResId:Int

)