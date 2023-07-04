package com.example.androidtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtest.databinding.FragmentViewimgBinding
import com.bumptech.glide.Glide
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.File

class imageViewer(val imgdata:ViewImg):Fragment() {
    private lateinit var binding:FragmentViewimgBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View?{
        binding= FragmentViewimgBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding= FragmentViewimgBinding.bind(view)
        Glide.with(this)
            .load(imgdata.path)
            .into(binding.imgview)

            binding.shareButton.setOnClickListener {
                shareImageWithText(imgdata.id)
            }
        }
    private fun shareImageWithText(imageid: Int) {
        val imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageid.toString()
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        startActivity(Intent.createChooser(shareIntent, "이미지 공유"))


    }
}

