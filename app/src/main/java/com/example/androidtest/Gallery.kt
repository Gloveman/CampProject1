package com.example.androidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.databinding.FragmentGalleryBinding
import com.example.recyclerview02.ListAdapterGrid

class Gallery : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        var list = arrayListOf("Img 1", "Img 2", "Img 3", "Img 4", "Img 5", "Img 6", "Img 7", "Img 8","Img 9","Img 10", "Img11")
        var listManager = GridLayoutManager(requireContext(), 2)
        var listAdapter = ListAdapterGrid(list)

        var recyclerList = view.findViewById<RecyclerView>(R.id.recyclerGridView).apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        return view
    }
}
