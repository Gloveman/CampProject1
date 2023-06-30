package com.example.androidtest

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.recyclerview.widget.RecyclerView
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class listadapter(private val context: Context?):RecyclerView.Adapter<listadapter.ViewHolder>(){
    var datas= mutableListOf<ContactData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.viewitem,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
                    .setTitle("Call")
                    .setMessage("call to ${number.text} ?")
                    .setPositiveButton("Call"
                    ) { dialog, which ->

                            val tst=Toast.makeText(context, "Call finished!", Toast.LENGTH_SHORT)
                            tst.setGravity(Gravity.BOTTOM, 0, 0)
                            tst.show()

                    }
                    .setNegativeButton("Cancel",null)
                    .create()
                    .show()
            }
        }
    }
}

data class ContactData(
    //val img:Int,
    val name:String,
    val number:String
)