package com.muratcan.moviescatalog.di

import com.muratcan.moviescatalog.viewmodel.MoviesDetailViewModel
import com.muratcan.moviescatalog.viewmodel.MoviesViewModel
import com.muratcan.moviescatalog.viewmodel.ActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by MuratCan on 17-10-2019.
 */

val viewModelModule: Module = module {
    viewModel { ActivityViewModel() }
    viewModel { MoviesViewModel() }
    viewModel { MoviesDetailViewModel() }
}