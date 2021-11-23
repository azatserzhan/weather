package kz.home.viewmodels.mainfeed.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway

class ClearMainFeedUseCase(
    private val mainFeedLocalGateway: MainFeedLocalGateway
) : UseCase<Unit, Unit>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit) {
        mainFeedLocalGateway.clear()
    }
}
