package kz.home.viewmodels.mainfeed.data.db

import androidx.paging.DataSource
import kz.home.common.models.MainFeed
import kz.home.common.util.safeWithTransaction
import kz.home.viewmodels.db.AppDatabase
import kz.home.viewmodels.mainfeed.data.MainFeedConverter
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway

private const val START_ITEM_INDEX = 0

internal class MainFeedDatabase(
    private val mainFeedDao: MainFeedDao,
    private val database: AppDatabase
) : MainFeedLocalGateway {
    override suspend fun save(mainFeeds: List<MainFeed>) {
        mainFeedDao.save(
            MainFeedConverter.toDatabase(
                mainFeeds = mainFeeds,
                insertOrder = mainFeedDao.getMaxInsertOrder()?.plus(1) ?: START_ITEM_INDEX
            )
        )
    }

    override suspend fun getAllAsDataSource(): DataSource.Factory<Int, MainFeed> =
        mainFeedDao.getAllAsDataSource().mapByPage { MainFeedConverter.fromDatabase(it) }

    override suspend fun clearAndSave(mainFeeds: List<MainFeed>) {
        database.safeWithTransaction {
            clear()
            save(mainFeeds)
        }
    }

    override suspend fun clear() {
        database.safeWithTransaction {
            mainFeedDao.clear()
        }
    }
}
