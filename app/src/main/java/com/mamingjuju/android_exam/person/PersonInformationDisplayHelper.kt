package com.mamingjuju.android_exam.person

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.util.*

object PersonInformationDisplayHelper {

    private fun computeAge(year: Int, month: Int, day: Int): Int {
        val today = Calendar.getInstance()
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        var age = today[Calendar.YEAR] - calendar[Calendar.YEAR]
        if(today[Calendar.MONTH] < calendar[Calendar.MONTH]) {
            age--
        }
        else if(today[Calendar.MONTH] == calendar[Calendar.MONTH]) {
            if (today[Calendar.DAY_OF_MONTH] < calendar[Calendar.DAY_OF_MONTH]) {
                age--
            }
        }

        return age
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun computeForAgeBasedFromBirthday(birthday: String?): Int {
        val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val birthday = LocalDate.parse(birthday, dateFormat)
        return computeAge(birthday.year, birthday.monthValue - 1, birthday.dayOfMonth)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(date: String?, oldFormat: String) : String{
        println("oldFormat: $oldFormat")
        val oldFormat = SimpleDateFormat(oldFormat)
        val parseDate = oldFormat.parse(date)
        println("parseDate: $parseDate")
        val newDateFormat = SimpleDateFormat("MMMM dd, yyyy").format(parseDate)
        return newDateFormat.toString()
    }
}