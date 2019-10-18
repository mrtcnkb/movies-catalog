package com.muratcan.moviescatalog.di

import com.muratcan.moviescatalog.api.provideOkhttpClient
import com.muratcan.moviescatalog.api.provideRetrofit
import com.muratcan.moviescatalog.api.provideService
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by MuratCan on 17-10-2019.
 */

val networkModule: Module = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}