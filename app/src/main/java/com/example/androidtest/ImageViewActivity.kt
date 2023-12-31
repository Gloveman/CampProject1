package com.example.androidtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.androidtest.databinding.ActivityImageviewerBinding
import kotlin.properties.Delegates

class ImageViewActivity:AppCompatActivity() {
    private lateinit var binding: ActivityImageviewerBinding
    val imgs= mutableListOf<ViewImg>()
    private var pos=0
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cursor= contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null)
        val curidx=cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while(cursor!!.moveToNext()){
            imgs.add(ViewImg(cursor.getString(curidx),cursor.getInt(cursor!!.getColumnIndex(MediaStore.MediaColumns._ID))))
        }
        pos=intent.getIntExtra("position",0)
        binding= ActivityImageviewerBinding.inflate(layoutInflater)
        binding.imageviewPager.adapter=imgviewpageradapter(imgs,supportFragmentManager, lifecycle)
        binding.imageviewPager.currentItem = pos
        window.statusBarColor= 0xffffff
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()

    }
}

data class ViewImg(
    val path:String,
    val id:Int
)