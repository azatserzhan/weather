package kz.home.viewmodels.db

import androidx.room.TypeConverter
import java.util.Date

internal class Converters {
    @TypeConverter
    fun longToDate(value: Long): Date = Date(value)

    @TypeConverter
    fun dateToLong(value: Date): Long = value.time
}
