package kz.home.viewmodels.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kz.home.viewmodels.mainfeed.data.db.MainFeedDao
import kz.home.viewmodels.mainfeed.data.db.MainFeedEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [
        MainFeedEntity::class
    ],
    views = [],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun mainFeedDao(): MainFeedDao

    companion object {
        const val DATABASE_NAME = "WeatherForecast"
    }
}
