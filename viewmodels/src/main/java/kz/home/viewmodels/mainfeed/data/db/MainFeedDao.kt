package kz.home.viewmodels.mainfeed.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MainFeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(mainFeeds: List<MainFeedEntity>)

    @Transaction
    @Query("select * from main_feed order by insert_order")
    fun getAllAsDataSource(): DataSource.Factory<Int, MainFeedEntity>

    @Query("delete from main_feed")
    fun clear()

    @Query("select max(insert_order) from main_feed")
    fun getMaxInsertOrder(): Int?
}
