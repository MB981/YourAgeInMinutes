package com.ageinminutes.application

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.ageinminutes.application.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        mBinding.btnSelectedDate.setOnClickListener { view ->

            DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    mBinding.tvDate.text = selectedDate
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = dateFormat.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000
                    Log.e("TAG_Date", "onCreate: Date-> ${theDate!!.time}" )
                    
                    
                    val currentDate =
                        dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                    Log.e("TAG_Date", "onCreate: CurrentSystemDate-> ${currentDate.toString()}" )

                    val currentDateInMinutes = currentDate!!.time / 60000
                    Log.e("TAG_Date", "onCreate: CurrentDate-> ${currentDate!!.time}" )

                    val differencesInMinutes = currentDateInMinutes - selectedDateInMinutes
                    mBinding.tvAgeInMinutes.text = differencesInMinutes.toString()
                },
                year,
                month,
                day
            ).show()
        }

    }
}