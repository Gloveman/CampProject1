package com.example.androidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidtest.databinding.FragmentGalleryBinding

class Gallery :Fragment(){ //dkdkdkdkd
    private lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater:LayoutInflater,
        container:ViewGroup?,
        savedInstanceState:Bundle?
    ):View?{
        binding= FragmentGalleryBinding.inflate(inflater,container,false)
        return binding.root
    }
}