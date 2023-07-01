package com.example.androidtest

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview02.ListAdapterGrid


class Gallery : Fragment() {
    val imgs= mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cursor= requireActivity().contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null)
        val curidx=cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while(cursor!!.moveToNext()){
            imgs.add(cursor.getString(curidx))
        }
        var listManager = GridLayoutManager(requireContext(), 2)
        var listAdapter = ListAdapterGrid(imgs,this,this.context)

        cursor.close()
        view.findViewById<RecyclerView>(R.id.recyclerGridView).apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }
        listAdapter.notifyDataSetChanged()
    }
}