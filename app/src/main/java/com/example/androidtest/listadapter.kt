package com.example.androidtest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

class listadapter(private val context: Context?):RecyclerView.Adapter<listadapter.ViewHolder>(){
    var datas= mutableListOf<ContactData>()
    private lateinit var listener:ContactListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.viewitem,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int=datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = datas[position].imageResId
        if (context != null) {
            Glide.with(context)
                .load(imageResId)
                .into(holder.itemView.findViewById(R.id.contactimg))
        }
        if(datas[position].number=="Group")
            holder.Groupbind(datas[position])
        else
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
                    .setItems(arrayOf("전화 걸기","문자 보내기","그룹에 추가하기")){ dialog, which ->
                        when(which)
                        {
                            0->{
                                val callIntent = Intent(Intent.ACTION_CALL);
                                callIntent.data = Uri.parse("tel:${number.text}")
                                context?.startActivity(callIntent, null)
                            }
                            1->{
                                val messageIntent=Intent(Intent.ACTION_VIEW)
                                messageIntent.data=Uri.parse("smsto:${number.text}")
                                context?.startActivity(messageIntent,null)
                            }
                            2->{
                                //fragment에서 처리
                                listener.AddtoGroup(data.name)
                            }
                        }

                    }
                    .create()
                    .show()
            }
        }
        fun Groupbind(data:ContactData){
            name.text=data.name
            number.text=data.number
            itemView.setOnClickListener{
                val groupview=Intent(context,GroupViewActivity::class.java)
                groupview.putExtra("groupname",data.name)
                context?.startActivity(groupview)
            }
        }
    }

    fun setAddtoGroupListener(func:(String)->Unit) {
        this.listener = object : ContactListener {

            override fun AddtoGroup(person: String) {
                func(person)
            }
        }
    }

}

    interface  ContactListener{
        fun AddtoGroup(person:String)
    }

    data class ContactData(
        val name:String,
        val number:String,
        val imageResId:Int

    )