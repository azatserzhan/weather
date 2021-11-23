package kz.home.viewmodels.mainfeed.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway

class ReloadMainFeedUseCase(
    private val getPreparedMainFeedUseCase: GetPreparedMainFeedUseCase,
    private val mainFeedLocalGateway: MainFeedLocalGateway
) : UseCase<ReloadMainFeedUseCase.Param, Unit>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param) {
        getPreparedMainFeedUseCase(GetPreparedMainFeedUseCase.Param(limit = parameters.limit)).also {
            mainFeedLocalGateway.clearAndSave(it)
        }
    }

    data class Param(val limit: Int)
}
