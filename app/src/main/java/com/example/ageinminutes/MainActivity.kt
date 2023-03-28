package com.example.ageinminutes

import android.app.DatePickerDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var Sdate: TextView? = null
    private var Rdate: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 : Button = findViewById(R.id.btn1)
        Sdate = findViewById(R.id.date)
        Rdate = findViewById(R.id.result)

        btn1.setOnClickListener{
            clickDatePicker()

        }
    }
   private  fun clickDatePicker(){

         val myCalendar = Calendar.getInstance()
         val year = myCalendar.get(Calendar.YEAR)
         val month = myCalendar.get(Calendar.MONTH)
         val day = myCalendar.get(Calendar.DAY_OF_MONTH)
         val dpd = DatePickerDialog(this, { view, Selectedyear, Selectedmonth, SelecteddayOfMonth ->


                 val Selecteddate = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"

                 Sdate?.setText(Selecteddate)

                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                 val theDate = sdf.parse(Selecteddate)
                 theDate?.let {
                     val selectedDateInMinutes = theDate.time / 60000
                     val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                     currentDate?.let {
                         val currentDateInMinutes = currentDate.time/ 60000
                         val differenceInMinutes =  currentDateInMinutes - selectedDateInMinutes
                         Rdate?.text = differenceInMinutes.toString()
                     }

                 }
             }, year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis()- 86400000
        dpd.show()

    }

}