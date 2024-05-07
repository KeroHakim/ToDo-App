package com.route.todoappc39g_mon_wed

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    fun Date.getDateOnly(): String{
        val format = SimpleDateFormat("dd / MM / yyyy", Locale.US)
        return format.format(this)
    }
    fun Date.getTimeOnly(): String{
        val format = SimpleDateFormat("hh : mm", Locale.US)
        return format.format(this)
    }
}