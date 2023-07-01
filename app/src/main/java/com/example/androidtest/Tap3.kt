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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.sql.Date


class Tap3 : Fragment() {
    private lateinit var dayText: TextView
    private lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayText = view.findViewById(R.id.day_text)
        calendarView = view.findViewById(R.id.calendarView)

        val dateFormat: DateFormat = DateFormat() //DateFormat("yyyy년 MM월 dd일")
        val date: Date = Date(calendarView.date)
        dayText.text = date.toString()

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val day: String = "${year}년 ${month + 1}월 ${dayOfMonth}일"
            dayText.text = day
        }
    }
}
