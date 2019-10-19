package com.muratcan.moviescatalog.di

import com.muratcan.moviescatalog.api.provideOkhttpClient
import com.muratcan.moviescatalog.api.provideRetrofit
import com.muratcan.moviescatalog.api.provideService
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by MuratCan on 2019-10-17.
 */

val networkModule: Module = module {
    single { provideOkhttpClient() }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}