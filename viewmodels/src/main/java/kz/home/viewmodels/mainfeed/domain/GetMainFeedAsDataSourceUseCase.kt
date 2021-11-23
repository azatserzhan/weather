package kz.home.viewmodels.mainfeed.domain

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.common.models.MainFeed
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway

class GetMainFeedAsDataSourceUseCase(
    private val mainFeedLocalGateway: MainFeedLocalGateway
) : UseCase<Unit, DataSource.Factory<Int, MainFeed>>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): DataSource.Factory<Int, MainFeed> =
        mainFeedLocalGateway.getAllAsDataSource()
}
