package kz.home.viewmodels.weather.data

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kz.home.common.models.Country
import kz.home.viewmodels.weather.domain.gateway.WeatherLocalGateway

internal class WeatherLocal : WeatherLocalGateway {
    override suspend fun getCounties(assetJSON: String): List<Country> {
        return GsonBuilder().create()
            .fromJson(assetJSON, object : TypeToken<ArrayList<Country>>() {}.type)
    }
}