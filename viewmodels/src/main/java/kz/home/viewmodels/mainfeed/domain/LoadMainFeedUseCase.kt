package kz.home.viewmodels.mainfeed.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway

class LoadMainFeedUseCase(
    private val getPreparedMainFeedUseCase: GetPreparedMainFeedUseCase,
    private val mainFeedLocalGateway: MainFeedLocalGateway
) : UseCase<LoadMainFeedUseCase.Param, Unit>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param) {
        getPreparedMainFeedUseCase(
            GetPreparedMainFeedUseCase.Param(nextVideoId = parameters.nextVideoId, limit = parameters.limit)
        ).also { if (it.isNotEmpty()) mainFeedLocalGateway.save(it) }
    }

    data class Param(val nextVideoId: String? = null, val limit: Int)
}
