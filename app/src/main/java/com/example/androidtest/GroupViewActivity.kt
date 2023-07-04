package com.example.androidtest

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.databinding.ActivityContactgroupBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.random.Random

class GroupViewActivity: AppCompatActivity() {
    var groupname=""
    val groupdata= mutableMapOf<String,MutableList<String>>()

    private lateinit var binding:ActivityContactgroupBinding
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactgroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupname= intent.getStringExtra("groupname").toString()
        Log.d("NAME",groupname)
        var fileinput: FileInputStream?=null
        try {
            fileinput= openFileInput("contactgroup.json")
        }
        catch(e: FileNotFoundException)
        {
            Log.d("NONOO","FILE ERROR")
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

        val cursor=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        while(cursor!!.moveToNext()){
            val name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            if(groupdata[groupname]!!.contains(name)) {
                val number =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val randomIndex = Random.nextInt(9)
                val imageResId = when (randomIndex) {
                    0 -> R.drawable.icon1
                    1 -> R.drawable.icon2
                    2 -> R.drawable.icon3
                    3 -> R.drawable.icon4
                    4 -> R.drawable.icon5
                    5 -> R.drawable.icon6
                    6 -> R.drawable.icon7
                    7 -> R.drawable.icon8
                    else -> R.drawable.default_image
                }
                GroupViewAdapter.members.add(ContactData(name,number,imageResId))
            }

        }
        binding.txtgroupname.text=groupname
        GroupViewAdapter.notifyDataSetChanged()

        GroupViewAdapter.setDeleteListener {
            AlertDialog.Builder(this)
                .setTitle("정말로 삭제합니까?")
                .setMessage("이름: ${it.name}")
                .setPositiveButton("네"){
                        dialog,which->
                    GroupViewAdapter.members.remove(it)
                    groupdata[groupname]?.remove(it.name)
                    GroupViewAdapter.notifyDataSetChanged()


                }
                .setNegativeButton("아니요",null)
                .create()
                .show()

        }
        binding.grouplist.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )

    }


    override fun onBackPressed() {

        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        val JSONFile= JSONArray()
        groupdata.forEach { (s, strings) ->
            var item= JSONObject()
            item.put("groupname",s)
            var people=JSONArray()
            strings.forEach {
                people.put(it)
            }
            item.put("member",people)
            JSONFile.put(item)
        }
        val JSONString=JSONFile.toString()
        val fileoutput: FileOutputStream?=this.openFileOutput("contactgroup.json", Context.MODE_PRIVATE)
        fileoutput?.write(JSONString.toByteArray())
        fileoutput?.close()
    }


}