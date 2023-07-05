package com.example.androidtest

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.util.Log
import com.google.android.material.tabs.TabLayoutMediator
import android.view.View
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.androidtest.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val permArray= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
                if (permArray.all {
                        ContextCompat.checkSelfPermission(this,it) == PackageManager.PERMISSION_GRANTED }) {
                    var vp = binding.viewPager
                    var tl = binding.tabLayout
                    vp.adapter = viewpageradapter(supportFragmentManager, lifecycle)
                   window.statusBarColor= 0xff1A2028.toInt()
                    TabLayoutMediator(tl, vp) { tab, position ->
                        when (position) {
                            0 -> {
                                tab.text = "Contacts"
                                tab.setIcon(R.drawable.contacticon)
                                tab.id=0
                                tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_LABELED
                            }
                            1 -> {
                                tab.text = "Gallery"
                                tab.id=1
                                //
                                tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                            }
                            2 -> {
                                tab.text = "Calendar"
                                tab.id=2
                                //
                                tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                            }
                        }

                    }.attach()
                    tl.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
                        override fun onTabSelected(tab: Tab?) {
                            tab?.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_LABELED
                            when(tab?.id){
                                0->tab.setIcon(R.drawable.contacticon)
                                1->tab.setIcon(R.drawable.galleryicon)
                                2->tab.setIcon(R.drawable.calendericon)
                            }
                        }

                        override fun onTabUnselected(tab: Tab?) {
                            tab?.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                            tab?.setIcon(R.drawable.nonvisibleicon)
                        }

                        override fun onTabReselected(tab: Tab?) {

                        }

                    })

                } else {
                    requestPermissions(permArray, 1357)
                }


    }

    override fun onResume() {
        super.onResume()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.all{it==PackageManager.PERMISSION_GRANTED}){
            var vp = binding.viewPager
            var tl = binding.tabLayout
            vp.adapter = viewpageradapter(supportFragmentManager, lifecycle)

            window.statusBarColor= 0xff1A2028.toInt()
            TabLayoutMediator(tl, vp) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Contacts"
                        tab.setIcon(R.drawable.contacticon)
                        tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                    }
                    1 -> {
                        tab.text = "Gallery"
                        tab.setIcon(R.drawable.galleryicon)
                        tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                    }
                    2 -> {
                        tab.text = "Calendar"
                        tab.setIcon(R.drawable.calendericon)
                        tab.tabLabelVisibility=TabLayout.TAB_LABEL_VISIBILITY_UNLABELED
                    }
                }
            }.attach()
            setContentView(binding.root)
        }
        else
            requestPermissions(permArray, 1357)

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("종료하시겠습니까?")
            // .setMessage("${name.text}에게 전화를 걸까요?")
            .setNegativeButton("네"){
                dialog,which-> this.finish()
            }
            .setPositiveButton("아니요",null)
            .create()
            .show()
    }
}