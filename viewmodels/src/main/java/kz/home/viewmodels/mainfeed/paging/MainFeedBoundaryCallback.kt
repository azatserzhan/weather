package kz.home.viewmodels.mainfeed.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kz.home.common.adapters.BaseContentItem
import kz.home.common.content.ItemContentType
import kz.home.common.models.NetworkState
import kz.home.common.models.PagingItem
import kz.home.common.util.launchSafe
import kz.home.viewmodels.mainfeed.domain.LoadMainFeedUseCase
import paging.PagingRequestHelper
import timber.log.Timber
import java.util.concurrent.Executors

internal class MainFeedBoundaryCallback(
    private val initialPageSize: Int,
    private val fetchPageSize: Int,
    private val loadMainFeedUseCase: LoadMainFeedUseCase,
    private val coroutineScope: CoroutineScope
) : PagedList.BoundaryCallback<BaseContentItem<ItemContentType>>() {
    private val helper = PagingRequestHelper(Executors.newSingleThreadExecutor())

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { callback ->
            coroutineScope.launchSafe(
                start = { _networkState.value = NetworkState.Loading() },
                body = {
                    loadMainFeedUseCase(LoadMainFeedUseCase.Param(limit = initialPageSize))
                    _networkState.value = NetworkState.Idle
                    callback.recordSuccess()
                },
                handleError = {
                    Timber.e(it, "Error while initial feed")
                    _networkState.value = NetworkState.Error(it)
                    callback.recordFailure(it)
                }
            )
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: BaseContentItem<ItemContentType>) {
        val cursor = (itemAtEnd as? PagingItem)?.cursorId
        if (cursor.isNullOrEmpty()) return
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { callback ->
            coroutineScope.launchSafe(
                start = { _networkState.value = NetworkState.Fetching },
                body = {
                    loadMainFeedUseCase(LoadMainFeedUseCase.Param(nextVideoId = cursor, limit = fetchPageSize))
                    _networkState.value = NetworkState.Idle
                    callback.recordSuccess()
                },
                handleError = {
                    Timber.e(it, "Error while loading feed")
                    _networkState.value = NetworkState.Error(it)
                    callback.recordFailure(it)
                }
            )
        }
    }
}
