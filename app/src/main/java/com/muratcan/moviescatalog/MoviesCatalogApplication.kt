package com.muratcan.moviescatalog

import android.app.Application
import android.content.Context
import com.muratcan.moviescatalog.di.networkModule
import com.muratcan.moviescatalog.di.repositoryModule
import com.muratcan.moviescatalog.di.viewModelModule
import com.muratcan.moviescatalog.util.Share
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by MuratCan on 17-10-2019.
 */

class MoviesCatalogApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Share.setInstance(this)
        startKoin {
            androidContext(this@MoviesCatalogApplication)
            modules(listOf(viewModelModule, networkModule, repositoryModule))
        }
    }

    init {
        instance = this
    }

    companion object {
        private var instance = MoviesCatalogApplication()
        val context: Context
            get() = instance.applicationContext
    }

}