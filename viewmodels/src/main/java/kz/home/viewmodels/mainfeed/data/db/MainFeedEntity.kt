package kz.home.viewmodels.mainfeed.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.home.viewmodels.mainfeed.data.db.MainFeedEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MainFeedEntity(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: String,
    @ColumnInfo(name = URL)
    val url: String,
    @ColumnInfo(name = INSERT_ORDER, index = true)
    val insertOrder: Int
) {
    companion object {
        const val TABLE_NAME = "main_feed"
        const val URL = "url"
        const val INSERT_ORDER = "insert_order"
        const val ID = "id"
    }
}
