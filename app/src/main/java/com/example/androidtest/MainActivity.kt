package com.example.androidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import android.view.View
import com.example.androidtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var vp=binding.viewPager
        var tl=binding.tabLayout
        vp.adapter=viewpageradapter(supportFragmentManager,lifecycle)

        TabLayoutMediator(tl,vp){tab,position->
            when(position){
                0->tab.text= "Contacts"
                1->tab.text= "Gallery"
            }
        }.attach()

        }
    override fun onResume() {
        super.onResume()

    }

}