package kz.home.common.models

sealed class NetworkState {
    object Idle : NetworkState()
    data class Loading(val shouldShowProgress: Boolean = true) : NetworkState()
    object Fetching : NetworkState()
    data class Error(val throwable: Throwable, val isRetryEnabled: Boolean = true) : NetworkState()
}
