package kz.home.viewmodels.mainfeed.data

import kz.home.common.interceptor.CommonInterceptor
import kz.home.common.interceptor.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.home.common.models.sticker.CameraStickerPack
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://camera.stipop.io/v1/"

interface CameraStickerApi {
    companion object {
        fun create(
            interceptor: CommonInterceptor,
            loggingInterceptor: LoggingInterceptor
        ): CameraStickerApi {
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.build())
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(CameraStickerApi::class.java)
        }
    }

    @GET("search")
    suspend fun search(
        @Query("userId") userId: String,
        @Query("searchText") searchText: String,
    ): CameraStickerPack
}