package kz.home.viewmodels.db

import androidx.room.Room
import androidx.room.RoomDatabase
import kz.home.viewmodels.db.AppDatabase
import kz.home.common.di.InjectionModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import java.util.concurrent.Executors

object AppDatabaseModule : InjectionModule {
    override fun create() = module {
        single {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                //.addMigrations(Migration.MIGRATION_1_2)
                .setQueryExecutor(Executors.newCachedThreadPool())
                .build()
        } bind RoomDatabase::class
        single { get<AppDatabase>().mainFeedDao() }
    }
}
