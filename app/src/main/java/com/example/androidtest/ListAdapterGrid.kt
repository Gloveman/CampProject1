package com.example.recyclerview02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.bumptech.glide.Glide
//import kotlinx.android.synthetic.main.list_grid_item.view.*

class ListAdapterGrid(val list: MutableList<String>,val act: Fragment,val context: Context?): RecyclerView.Adapter<ListAdapterGrid.GridAdapter>() {

    class GridAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter {
        var view = LayoutInflater.from(context).inflate(R.layout.list_grid_item, parent, false)
        return GridAdapter(view)
    }

    override fun onBindViewHolder(holder: GridAdapter, position: Int) {
        val item = list[position]
        Glide.with(act)
            .load(item)
            .into(holder.itemView.findViewById(R.id.img))
        //holder.layout.findViewById<TextView>(R.id.textListTitle).text = item


        holder.layout.setOnClickListener {
            Toast.makeText(holder.layout.context, "$item Click!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}