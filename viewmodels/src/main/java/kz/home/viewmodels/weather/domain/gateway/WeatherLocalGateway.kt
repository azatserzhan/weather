package kz.home.viewmodels.weather.domain.gateway

import androidx.paging.DataSource
import kz.home.common.models.Country
import kz.home.common.models.MainFeed

interface WeatherLocalGateway {
    suspend fun getCounties(assetJSON: String): List<Country>
}
