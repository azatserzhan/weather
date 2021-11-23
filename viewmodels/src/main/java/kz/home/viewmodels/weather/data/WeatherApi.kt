package kz.home.viewmodels.weather.data

import kz.home.common.interceptor.CommonInterceptor
import kz.home.common.interceptor.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.home.common.models.sticker.CameraStickerPack
import kz.home.common.models.weather.CurrentWeather
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val WEATHER_API_ID = "0bc9bc2a73fd9644f664cf5f5c5be8d7"

interface WeatherApi {
    companion object {
        fun create(
            loggingInterceptor: LoggingInterceptor
        ): WeatherApi {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.build())
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(WeatherApi::class.java)
        }
    }

    @GET("weather")
    suspend fun weather(
        @Query("id") cityId: String,
        @Query("appid") appId: String = WEATHER_API_ID,
    ): CurrentWeather
}