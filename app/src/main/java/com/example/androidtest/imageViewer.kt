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
    }
}