package com.applichic.chicsecret.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        if (value == null) return null

        val cal = GregorianCalendar()
        cal.timeInMillis = value
        return cal
    }

    @TypeConverter
    fun toTimestamp(timestamp: Calendar?): Long? {
        if (timestamp == null) return null

        return timestamp.timeInMillis
    }
}