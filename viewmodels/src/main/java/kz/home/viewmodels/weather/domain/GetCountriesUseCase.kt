package kz.home.viewmodels.weather.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kz.home.common.base.UseCase
import kz.home.common.models.Country
import kz.home.viewmodels.weather.domain.gateway.WeatherLocalGateway

class GetCountriesUseCase(
    private val weatherLocalGateway: WeatherLocalGateway
) : UseCase<GetCountriesUseCase.Param, List<Country>>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Param) =
        weatherLocalGateway.getCounties(parameters.assetJSON)

    data class Param(val assetJSON: String)
}
