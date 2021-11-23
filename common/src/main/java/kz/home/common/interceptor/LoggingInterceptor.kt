package kz.home.common.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object LoggingInterceptor {

    fun build(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}