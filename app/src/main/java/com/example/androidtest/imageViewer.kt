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

class imageViewer(val imgpath:String):Fragment() {
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
            .load(imgpath)
            .into(binding.imgview)

            binding.shareButton.setOnClickListener {
                shareImage(imgpath)
            }
        }
    private fun shareImage(imagePath: String) {
        val imageUri = Uri.parse(imagePath)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        startActivity(Intent.createChooser(shareIntent, "이미지 공유"))
    }
}

