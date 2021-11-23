package kz.home.common

import kz.home.common.di.InjectionModule
import kz.home.common.interceptor.CommonInterceptor
import kz.home.common.interceptor.LoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module

object CommonModule : InjectionModule {
    override fun create(): Module = module {
        single { CommonInterceptor() }
        single { LoggingInterceptor }
    }
}