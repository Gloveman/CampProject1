package com.example.androidtest

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val permArray= arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

                if (permArray.all {
                        ContextCompat.checkSelfPermission(this,it) == PackageManager.PERMISSION_GRANTED }) {
                    var vp = binding.viewPager
                    var tl = binding.tabLayout
                    vp.adapter = viewpageradapter(supportFragmentManager, lifecycle)

                    TabLayoutMediator(tl, vp) { tab, position ->
                        when (position) {
                            0 -> {
                                tab.text = "Contacts"
                                tab.setIcon(R.drawable.contacticon)
                            }
                            1 -> {
                                tab.text = "Gallery"
                                tab.setIcon(R.drawable.contacticon)
                            }
                            2 -> {
                                tab.text = "Calendar"
                                tab.setIcon(R.drawable.contacticon)
                            }
                        }
                    }.attach()
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
        if(grantResults.all{it==0}){
            var vp = binding.viewPager
            var tl = binding.tabLayout
            vp.adapter = viewpageradapter(supportFragmentManager, lifecycle)

            TabLayoutMediator(tl, vp) { tab, position ->
                when (position) {
                    0 -> tab.text = "Contacts"
                    1 -> tab.text = "Gallery"
                    2 -> tab.text = "Calendar"
                }
            }.attach()
        }
        else
            requestPermissions(permArray, 1357)

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("종료하시겠습니까?")
            // .setMessage("${name.text}에게 전화를 걸까요?")
            .setPositiveButton("네"){
                dialog,which-> this.finish()
            }
            .setNegativeButton("아니요",null)
            .create()
            .show()
    }
}