package kz.home.weatherforecast

import kz.home.common.CommonModule
import kz.home.common.content.ContentModule
import kz.home.viewmodels.db.AppDatabaseModule
import kz.home.viewmodels.mainfeed.MainFeedModule
import kz.home.viewmodels.weather.WeatherModule
import org.koin.core.module.Module

internal object KoinModules {
    fun create(): List<Module> = listOf(
        AppModule.create(),
        AppDatabaseModule.create(),
        MainFeedModule.create(),
        WeatherModule.create(),
        ContentModule.create(),
        CommonModule.create()
    )
}
