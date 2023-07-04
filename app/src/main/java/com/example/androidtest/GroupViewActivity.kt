package com.example.androidtest

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import com.example.androidtest.databinding.ActivityContactgroupBinding
import org.json.JSONArray
import java.io.FileInputStream
import java.io.FileNotFoundException

class GroupViewActivity: AppCompatActivity() {
    var groupname=""
    val groupdata= mutableMapOf<String,MutableList<String>>()
    private lateinit var binding:ActivityContactgroupBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding= ActivityContactgroupBinding.inflate(layoutInflater)
        groupname= intent.getStringExtra("groupname").toString()

        var fileinput: FileInputStream?=null
        try {
            fileinput= openFileInput("contactgroup.json")
        }
        catch(e: FileNotFoundException)
        {
        }

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
        }


        val GroupViewAdapter=groupviewadapter(this)
        binding.grouplist.adapter=GroupViewAdapter
        binding.txtgroupname.text=groupname


        setContentView(binding.root)
    }
}