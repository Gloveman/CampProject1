package com.example.androidtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.databinding.FragmentContactinfoBinding
import android.provider.ContactsContract
import android.database.Cursor
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlin.random.Random


class ContactInfo:Fragment() {
    private lateinit var binding:FragmentContactinfoBinding
    lateinit var ListAdapter:listadapter
    val datas= mutableListOf<ContactData>()
    var query=""
    override fun onCreateView(
        inflater:LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View?{
        binding= FragmentContactinfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentContactinfoBinding.bind(view)

        val cursor=requireActivity().contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        while(cursor!!.moveToNext()){

            val name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val randomIndex = Random.nextInt(9)
            val imageResId = when(randomIndex){
                0 ->R.drawable.icon1
                1 ->R.drawable.icon2
                2 ->R.drawable.icon3
                3 ->R.drawable.icon4
                4 ->R.drawable.icon5
                5 ->R.drawable.icon6
                6 ->R.drawable.icon7
                7 ->R.drawable.icon8
                else -> R.drawable.default_image
            }
            datas.add(ContactData(name=name,number=number,imageResId = imageResId))
        }
        ListAdapter= listadapter(this.context)
        binding.contactlist.adapter=ListAdapter
        ListAdapter.datas=datas
        ListAdapter.notifyDataSetChanged()
        cursor.close()
        binding.queryname.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                query=p0.toString()
                if(query=="")
                    ListAdapter.datas=datas
                else
                {
                    ListAdapter.datas=datas.filter{it.name.contains(query)}.toMutableList()
                }
                ListAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.contactlist.addItemDecoration(DividerItemDecoration(this.context,LinearLayoutManager.VERTICAL))
        //binding.textView.text=S;
    }

}