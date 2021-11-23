package kz.home.viewmodels.mainfeed.data.db

import androidx.room.Embedded
import kz.home.viewmodels.mainfeed.data.db.MainFeedEntity

class MainFeedDetail {
    @Embedded
    lateinit var mainFeedEntity: MainFeedEntity
}
