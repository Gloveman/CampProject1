package com.example.androidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtest.databinding.FragmentContactinfoBinding
import org.json.JSONArray

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentContactinfoBinding.bind(view)
        val inputStream=resources.assets.open("contact.json")
        val size=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val contactarray=JSONArray(String(buffer,Charsets.UTF_8))
        ListAdapter= listadapter(this.context)
        binding.contactlist.adapter=ListAdapter
        for (i in 0 until contactarray.length()){
            val single=contactarray.getJSONObject(i)
            val name=single.getString("name")
            val number=single.getString("number")
            datas.add(ContactData(name=name,number=number))
        }
        ListAdapter.datas=datas
        ListAdapter.notifyDataSetChanged()
        //binding.textView.text=S;
    }

}