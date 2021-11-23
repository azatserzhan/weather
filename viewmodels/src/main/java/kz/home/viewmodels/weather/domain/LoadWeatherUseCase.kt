package kz.home.viewmodels.weather.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.common.models.weather.CurrentWeather
import kz.home.viewmodels.weather.data.WeatherApi

class LoadWeatherUseCase(
    private val weatherApi: WeatherApi
) : UseCase<LoadWeatherUseCase.Param, CurrentWeather>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param) =
        weatherApi.weather(parameters.cityId)

    data class Param(val cityId: String)
}
