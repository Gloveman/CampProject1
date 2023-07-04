package com.example.androidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import com.example.androidtest.databinding.ActivityContactgroupBinding
class GroupViewActivity: AppCompatActivity() {
    var groupname=""
    private lateinit var binding:ActivityContactgroupBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding= ActivityContactgroupBinding.inflate(layoutInflater)

        groupname= intent.getStringExtra("groupname").toString()


        setContentView(binding.root)
    }
}