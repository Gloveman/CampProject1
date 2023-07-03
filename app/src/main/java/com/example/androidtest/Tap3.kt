package com.example.androidtest

import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
//import  kotlinx.android.synthetic.main.activity_main
import android.view.LayoutInflater
import java.text.SimpleDateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import com.example.androidtest.databinding.Tap3Binding
import java.sql.Date


class Tap3 : Fragment() {
    private lateinit var daykey: String
    private lateinit var binding:Tap3Binding
    val mapdatas= mutableMapOf<String,MutableList<MemoData>>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=Tap3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendarView = binding.calendarView
        val memolistAdapter=memolistadapter(this.context)
        val dummydata=mutableListOf(MemoData("Test1","finish homework"), MemoData("Test2","finish coding"),MemoData("Test3","playing game"))
        //Get memos


        memolistAdapter.datas= dummydata
        //mapdatas.put(key,list)
        //memolistAdapter.datas=mapdatas.getOrDefault("", mutableListOf())

        binding.memolist.adapter=memolistAdapter

       // val dateFormat: DateFormat = DateFormat() //DateFormat("yyyy년 MM월 dd일")
       // val date: Date = Date(calendarView.date)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->

           // val day: String = "${year}년 ${month + 1}월 ${dayOfMonth}일"
            //dayText.text = day
            val testmap= mutableMapOf<String,MutableList<MemoData>>()
        }
        binding.btnnew.setOnClickListener {
            //추가 창 열기
            memolistAdapter.notifyDataSetChanged()
        }
        binding.btnchange.setOnClickListener {
            val testdata=memolistAdapter.selecteddata
            if(testdata== MemoData("",""))
                Toast.makeText(this.context,"메모를 선택하세요", Toast.LENGTH_SHORT).show()
            else
            {
                //수정 창 열기
            }
            memolistAdapter.notifyDataSetChanged()
        }
        binding.btndel.setOnClickListener {
            //삭제 여부 확인하기
            memolistAdapter.notifyDataSetChanged()
        }

        binding.memolist.addItemDecoration(
            DividerItemDecoration(this.context,
                LinearLayoutManager.VERTICAL)
        )
    }

}
