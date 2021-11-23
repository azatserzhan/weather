package kz.home.viewmodels.mainfeed.data

import kz.home.common.models.MainFeed
import kz.home.viewmodels.mainfeed.data.db.MainFeedEntity
import java.util.UUID

internal object MainFeedConverter {

    fun fromDatabase(list: List<MainFeedEntity>) = list.map { fromDatabase(it) }

    fun toDatabase(mainFeeds: List<MainFeed>, insertOrder: Int): List<MainFeedEntity> =
        mainFeeds.mapIndexed { index, mainFeed -> toDatabase(mainFeed, insertOrder + index) }

    private fun fromDatabase(item: MainFeedEntity) = MainFeed(item.id, item.url)

    private fun toDatabase(mainFeed: MainFeed, insertOrder: Int): MainFeedEntity =
        MainFeedEntity(
            id = mainFeed.id + UUID.randomUUID().toString(),
            url = mainFeed.url,
            insertOrder = insertOrder
        )
}
