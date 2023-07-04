package com.example.androidtest

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import groupCustomDialog
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.random.Random


class ContactInfo:Fragment() {
    private lateinit var binding:FragmentContactinfoBinding
    lateinit var ListAdapter:listadapter
    val datas= mutableListOf<ContactData>()
    val groupdata= mutableMapOf<String,MutableList<String>>()
    var query=""
    override fun onCreateView(
        inflater:LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View?{
        binding= FragmentContactinfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding=FragmentContactinfoBinding.bind(view)

        //Group 불러오기
        var fileinput: FileInputStream?=null
        try {
            fileinput= this.context?.openFileInput("contactgroup.json")
        }
        catch(e: FileNotFoundException)
        {
        }
        if (fileinput != null) {

            val buffer = ByteArray(fileinput!!.available())
            fileinput.read(buffer)
            fileinput.close()

            val dataArray = JSONArray(String(buffer, Charsets.UTF_8))
            for (i in 0 until dataArray.length()) {
                val item = dataArray.getJSONObject(i)
                val people = mutableListOf<String>()
                val members=item.getJSONArray("member")
                for(j in 0 until members.length()) {
                    people.add(members.getString(j))
                }
                groupdata[item.getString("groupname")] = people
                datas.add(ContactData(item.getString("groupname"),"Group", Color.GREEN))
            }
        }
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
        ListAdapter.setAddtoGroupListener { s ->
            val temp=groupdata.keys.toMutableList()
            var i=0
            temp.add("새 그룹")
            val grouplist= arrayOfNulls<String>(temp.size)
            temp.forEach {
                grouplist[i]=it
                i++
            }
            AlertDialog.Builder(context)
                .setTitle("그룹 선택")
                .setItems(grouplist){ dialog, which ->
                    if(which!=temp.size-1) {
                        groupdata.getOrPut(temp[which]) { mutableListOf() }.add(s)
                        updateGroup()
                        Toast.makeText(this.context, "추가 완료", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val dialog=groupCustomDialog(requireContext())
                        dialog.setOnClickListener {
                            groupdata.getOrPut(it) { mutableListOf() }.add(s)
                            updateGroup()
                            Toast.makeText(this.context, "추가 완료", Toast.LENGTH_SHORT).show()
                        }
                        dialog.showDialog()
                    }

                }
                .create()
                .show()

        }
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
    }
    override fun onDestroyView() {
        super.onDestroyView()
        updateGroup()
    }
    fun updateGroup()
    {
        val JSONFile= JSONArray()
        groupdata.forEach { (s, strings) ->
            var item=JSONObject()
            item.put("groupname",s)
            var people=JSONArray()
            strings.forEach {
                people.put(it)
            }
            item.put("member",people)
            JSONFile.put(item)
        }
        val JSONString=JSONFile.toString()
        val fileoutput: FileOutputStream?=this.context?.openFileOutput("contactgroup.json", Context.MODE_PRIVATE)
        fileoutput?.write(JSONString.toByteArray())
        fileoutput?.close()
    }

}