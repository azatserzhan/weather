package kz.home.viewmodels.weather

import kz.home.common.di.InjectionModule
import kz.home.viewmodels.mainfeed.viewmodel.WeatherViewModel
import kz.home.viewmodels.weather.data.WeatherApi
import kz.home.viewmodels.weather.data.WeatherLocal
import kz.home.viewmodels.weather.domain.GetCountriesUseCase
import kz.home.viewmodels.weather.domain.LoadWeatherUseCase
import kz.home.viewmodels.weather.domain.gateway.WeatherLocalGateway
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object WeatherModule : InjectionModule {
    override fun create(): Module = module {
        viewModel { WeatherViewModel(get(), get()) }
        single { WeatherApi.create(get()) }
        single { LoadWeatherUseCase(get()) }
        single { GetCountriesUseCase(get()) }
        single <WeatherLocalGateway>{ WeatherLocal() }
    }
}
