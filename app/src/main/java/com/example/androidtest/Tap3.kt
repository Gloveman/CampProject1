package com.example.androidtest

import CustomDialog
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.databinding.Tap3Binding
import java.util.Calendar
import java.util.Random
import org.json.JSONArray
import org.json.JSONObject
import java.io.*


class Tap3 : Fragment() {
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
        val curdate=Calendar.getInstance()
        datekey=curdate.get(Calendar.YEAR).toString()+(curdate.get(Calendar.MONTH)+1).toString()+curdate.get(Calendar.DAY_OF_MONTH).toString()
        var fileinput: FileInputStream?=null
        try {
            fileinput= this.context?.openFileInput("memodata.json")
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
             memolistAdapter.notifyDataSetChanged()

            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                datekey = year.toString() + (month + 1).toString() + dayOfMonth.toString()
                memolistAdapter.datas = mapdatas.getOrDefault(datekey, mutableListOf())
                memolistAdapter.notifyDataSetChanged()
            }
            binding.btnnew.setOnClickListener {
                //추가 창 열기
                val dialog=CustomDialog(requireContext())

                dialog.setonOKClickedListner { title,memo ->
                    mapdatas.getOrPut(datekey){mutableListOf()}.add(MemoData(title,memo))
                    memolistAdapter.datas = mapdatas.getOrDefault(datekey, mutableListOf())
                    memolistAdapter.notifyDataSetChanged()
                }
                dialog.showDialog()


            }
            binding.btnchange.setOnClickListener {
                val testdata = memolistAdapter.selecteddata
                if (testdata == MemoData("", ""))
                    Toast.makeText(this.context, "메모를 선택하세요", Toast.LENGTH_SHORT).show()
                else {
                    val memlist=mapdatas[datekey]
                    //testdata를 dialog로 전달
                    val dialog=CustomDialog(requireContext())
                    dialog.setMemoData(testdata)
                    dialog.setonOKClickedListner { title,memo ->
                        memlist?.set(memlist.indexOf(testdata),MemoData(title,memo))
                        memolistAdapter.datas = mapdatas.getOrDefault(datekey, mutableListOf())
                        memolistAdapter.lastitem.setBackgroundColor(Color.WHITE)
                        memolistAdapter.notifyDataSetChanged()
                    }
                    dialog.showDialog()

                }

            }
            binding.btndel.setOnClickListener {
                val testdata = memolistAdapter.selecteddata
                if (testdata == MemoData("", ""))
                    Toast.makeText(this.context, "메모를 선택하세요", Toast.LENGTH_SHORT).show()
                else {
                    AlertDialog.Builder(this.context)
                        .setTitle("정말 이 메모를 삭제합니까?")
                        .setMessage("제목: ${testdata.title}")
                        .setPositiveButton("네"){
                                dialog,which->
                            mapdatas[datekey]?.remove(testdata)
                            memolistAdapter.selecteddata= MemoData("","")
                            memolistAdapter.datas = mapdatas.getOrDefault(datekey, mutableListOf())
                            memolistAdapter.lastitem.setBackgroundColor(Color.WHITE)
                            memolistAdapter.notifyDataSetChanged()
                        }
                        .setNegativeButton("아니요",null)
                        .create()
                        .show()
                }

            }

            binding.memolist.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    LinearLayoutManager.VERTICAL
                )
            )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        val JSONFile=JSONArray()
        mapdatas.forEach { (s, memoData) ->
            var item=JSONObject()
            item.put("key",s)
            var memolist=JSONArray()
            memoData.forEach {
                var memoitem=JSONObject()
                memoitem.put("title",it.title)
                memoitem.put("memo",it.memo)
                memolist.put(memoitem)
            }
            item.put("memolist",memolist)
            JSONFile.put(item)
        }
        val JSONString=JSONFile.toString()
        val fileoutput:FileOutputStream?=this.context?.openFileOutput("memodata.json",Context.MODE_PRIVATE)
        fileoutput?.write(JSONString.toByteArray())
        fileoutput?.close()
    }





}
