package kz.home.common.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class CommonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("apikey",  "e38cb8b67313cfaaeb162fb49d484451") //TODO: change it
            .build()

        return chain.proceed(request)
    }
}