package kz.home.viewmodels.mainfeed.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.common.models.MainFeed
import kz.home.viewmodels.mainfeed.data.CameraStickerApi

class GetPreparedMainFeedUseCase(
    private val cameraStickerApi: CameraStickerApi
) : UseCase<GetPreparedMainFeedUseCase.Param, List<MainFeed>>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param): List<MainFeed> =
        cameraStickerApi.search("1234", "dog").stickerBody.stickerList.map {
            MainFeed(it.stickerId.toString(), it.stickerImgUrl)
        }

    data class Param(val nextVideoId: String? = null, val limit: Int)
}
