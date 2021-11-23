package kz.home.viewmodels.mainfeed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.home.common.base.BaseViewModel
import kz.home.common.content.items.CityItem
import kz.home.common.models.Country
import kz.home.common.models.NetworkState
import kz.home.common.models.weather.CurrentWeather
import kz.home.common.util.SingleLiveEvent
import kz.home.common.util.launchSafe
import kz.home.viewmodels.weather.domain.GetCountriesUseCase
import kz.home.viewmodels.weather.domain.LoadWeatherUseCase
import timber.log.Timber

class WeatherViewModel(
    val loadWeatherUseCase: LoadWeatherUseCase,
    val getCountriesUseCase: GetCountriesUseCase
) : BaseViewModel() {
    private val _actions = SingleLiveEvent<HomeAction>()
    val actions: LiveData<HomeAction> = _actions

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _cityItems = MutableLiveData<List<CityItem>>()
    val cityItems: LiveData<List<CityItem>> = _cityItems

    private val _state = MutableLiveData<NetworkState>()
    val state: LiveData<NetworkState> = _state

    fun getCountries(assetJSON: String) {
        launchSafe(
            body = {
                val countrues = getCountriesUseCase(GetCountriesUseCase.Param(assetJSON))
                _countries.postValue(countrues)
                _cityItems.postValue(countrues.map {
                    CityItem(
                        it.country.orEmpty(),
                        it.name.orEmpty(),
                        onItemClickListener = {
                            loadWeatherCity(it.name.orEmpty())
                        },
                    )
                })
            },
            handleError = {
                Timber.e(it, "Error to get counties")
            }
        )
    }

    fun loadWeatherCity(cityName: String) {
        launchSafe(
            body = {
                val city = countries.value?.findLast { it.name == cityName }
                city?.let {
                    _actions.postValue(HomeAction.ShowCityWeather(loadWeatherUseCase(LoadWeatherUseCase.Param(it.Id.toString()))))
                }
            },
            handleError = {
                Timber.e(it, "Error to get counties")
            }
        )
    }
}

sealed class HomeAction {
    data class ShowCityWeather(val currentWeather: CurrentWeather) : HomeAction()
}
