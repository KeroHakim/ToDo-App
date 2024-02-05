package com.route.todoappc39g_mon_wed.database

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {
    // 29 / 1 / 2024 -> 510131321
    @TypeConverter
    fun convertToDate(dateTime: Long): Date {
        return Date(dateTime)
    }

    @TypeConverter
    fun convertFromDate(date: Date): Long {
        return date.time
    }

}