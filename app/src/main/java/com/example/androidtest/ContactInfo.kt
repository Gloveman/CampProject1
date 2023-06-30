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
import org.json.JSONArray
import android.provider.ContactsContract
import android.database.Cursor
import android.provider.ContactsContract.Contacts


class ContactInfo:Fragment() {
    private lateinit var binding:FragmentContactinfoBinding
    lateinit var ListAdapter:listadapter
    val datas= mutableListOf<ContactData>()
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

        val cursor=requireActivity().contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        while(cursor!!.moveToNext()){

            val name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            datas.add(ContactData(name=name,number=number))
        }
        ListAdapter= listadapter(this.context)
        binding.contactlist.adapter=ListAdapter

        cursor.close()
        ListAdapter.datas=datas
        ListAdapter.notifyDataSetChanged()
        binding.contactlist.addItemDecoration(DividerItemDecoration(this.context,LinearLayoutManager.VERTICAL))
        //binding.textView.text=S;
    }

}