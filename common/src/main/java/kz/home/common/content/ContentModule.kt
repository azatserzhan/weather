package kz.home.common.content

import kz.home.common.adapters.ContentAdapter
import kz.home.common.adapters.PagedContentAdapter
import kz.home.common.di.InjectionModule
import org.koin.core.module.Module
import org.koin.dsl.module

object ContentModule : InjectionModule {

    override fun create(): Module = module {
        factory { ContentAdapter(ItemContentType.values()) }
        factory { PagedContentAdapter(ItemContentType.values()) }
    }
}
