package com.example.androidtest

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.databinding.Tap3Binding
import java.util.Calendar
import org.json.JSONArray
import org.json.JSONObject
import java.io.*


class Tap3 : Fragment() {
    private lateinit var daykey: String
    private lateinit var binding:Tap3Binding
    val mapdatas= mutableMapOf<String,MutableList<MemoData>>()
    var datekey=""
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
        binding.memolist.adapter=memolistAdapter
        binding.labeltitle.text="Before Changed"
        val dummydata=mutableListOf(MemoData("Test1","finish homework"), MemoData("Test2","finish coding"),MemoData("Test3","playing game"))
        val curdate=Calendar.getInstance()
        datekey=curdate.get(Calendar.YEAR).toString()+(curdate.get(Calendar.MONTH)+1).toString()+curdate.get(Calendar.DAY_OF_MONTH).toString()
        //Get memos
        //val file=File(context?.filesDir,"memo.json")
        var fileinput: FileInputStream?=null
        try {
            fileinput= this.context?.openFileInput("test.json")
        }
        catch(e:FileNotFoundException)
        {

        }
            if (fileinput != null) {

                val buffer = ByteArray(fileinput!!.available())
                fileinput.read(buffer)
                fileinput.close()

                val dataArray = JSONArray(String(buffer, Charsets.UTF_8))
                for (i in 0 until dataArray.length()) {
                    val item = dataArray.getJSONObject(i)
                    val memolist = mutableListOf<MemoData>()
                    val memos=item.getJSONArray("memolist")
                    for(j in 0 until memos.length()) {
                        val memoitem=memos.getJSONObject(j)
                        memolist.add(MemoData(memoitem.getString("title"),memoitem.getString("memo")))
                    }
                    mapdatas[item.getString("key")] = memolist
                }
            }
            memolistAdapter.datas = mapdatas.getOrDefault(datekey, mutableListOf())


            //memolistAdapter.datas = dummydata
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                datekey = year.toString() + (month + 1).toString() + dayOfMonth.toString()

            }
            binding.btnnew.setOnClickListener {
                //추가 창 열기

                memolistAdapter.notifyDataSetChanged()
            }
            binding.btnchange.setOnClickListener {
                val testdata = memolistAdapter.selecteddata
                if (testdata == MemoData("", ""))
                    Toast.makeText(this.context, "메모를 선택하세요", Toast.LENGTH_SHORT).show()
                else {

                    //수정 창 열기
                }
                memolistAdapter.notifyDataSetChanged()
            }
            binding.btndel.setOnClickListener {
                val testdata = memolistAdapter.selecteddata
                if (testdata == MemoData("", ""))
                    Toast.makeText(this.context, "메모를 선택하세요", Toast.LENGTH_SHORT).show()
                else {
                    //삭제 여부 확인
                }
                memolistAdapter.notifyDataSetChanged()
            }

            binding.memolist.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    LinearLayoutManager.VERTICAL
                )
            )

    }

    override fun onDestroy() {
        super.onDestroy()
        //file save
    }

}
