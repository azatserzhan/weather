package kz.home.viewmodels.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

@Dao
abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertIgnore(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertIgnore(entities: List<T>): List<Long>

    @Update
    abstract fun update(entity: T)

    @Update
    abstract fun update(entities: List<T>)

    @Transaction
    open fun insertOrUpdate(entity: T) {
        insertIgnore(entity).also { if (it == -1L) update(entity) }
    }

    @Transaction
    open fun insertOrUpdate(entities: List<T>) {
        val updateList = mutableListOf<T>()
        val ids = insertIgnore(entities)
        ids.forEachIndexed { index, id -> if (id == -1L) updateList.add(entities[index]) }
        if (updateList.isNotEmpty()) update(updateList)
    }
}
