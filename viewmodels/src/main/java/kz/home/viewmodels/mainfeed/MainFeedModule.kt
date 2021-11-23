package kz.home.viewmodels.mainfeed

import kz.home.common.di.InjectionModule
import kz.home.viewmodels.mainfeed.data.CameraStickerApi
import kz.home.viewmodels.mainfeed.data.db.MainFeedDatabase
import kz.home.viewmodels.mainfeed.domain.ClearMainFeedUseCase
import kz.home.viewmodels.mainfeed.domain.GetMainFeedAsDataSourceUseCase
import kz.home.viewmodels.mainfeed.domain.GetPreparedMainFeedUseCase
import kz.home.viewmodels.mainfeed.domain.LoadMainFeedUseCase
import kz.home.viewmodels.mainfeed.domain.ReloadMainFeedUseCase
import kz.home.viewmodels.mainfeed.domain.gateway.MainFeedLocalGateway
import org.koin.core.module.Module
import org.koin.dsl.module

object MainFeedModule : InjectionModule {
    override fun create(): Module = module {
        single { GetMainFeedAsDataSourceUseCase(get()) }
        single { LoadMainFeedUseCase(get(), get()) }
        single<MainFeedLocalGateway> { MainFeedDatabase(get(), get()) }
        single { CameraStickerApi.create(get(), get()) }
        single { ReloadMainFeedUseCase(get(), get()) }
        single { GetPreparedMainFeedUseCase(get()) }
        single { ClearMainFeedUseCase(get()) }
    }
}
