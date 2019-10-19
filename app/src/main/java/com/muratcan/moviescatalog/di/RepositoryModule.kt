package com.muratcan.moviescatalog.di

import com.muratcan.moviescatalog.repository.MoviesRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by MuratCan on 2019-10-17.
 */

val repositoryModule: Module = module {
    single { MoviesRepository(get()) }
}