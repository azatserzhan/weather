package kz.home.viewmodels.mainfeed.domain.gateway

import androidx.paging.DataSource
import kz.home.common.models.MainFeed

interface MainFeedLocalGateway {
    suspend fun save(mainFeeds: List<MainFeed>)
    suspend fun getAllAsDataSource(): DataSource.Factory<Int, MainFeed>
    suspend fun clearAndSave(mainFeeds: List<MainFeed>)
    suspend fun clear()
}
