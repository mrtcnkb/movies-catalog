package com.muratcan.moviescatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.muratcan.moviescatalog.datasource.factory.MoviesDataSourceFactory
import com.muratcan.moviescatalog.model.ParameterTypeEnum
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.ui.listener.DefaultSelectListener

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesViewModel : ViewModel() {

    var isError = MutableLiveData<Boolean>()

    var moviesListPopular: LiveData<PagedList<Result>>? = null
    var moviesListTopRated: LiveData<PagedList<Result>>? = null
    var moviesListRevenue: LiveData<PagedList<Result>>? = null
    var moviesListReleaseDate: LiveData<PagedList<Result>>? = null

    fun preparePagingFactories(defSelect: DefaultSelectListener){
        popularPagingFactory(defSelect)
        topRatedPagingFactory(defSelect)
        revenuePagingFactory(defSelect)
        releaseDatePagingFactory(defSelect)
    }

    private fun popularPagingFactory(defaultSelect: DefaultSelectListener){
        if (moviesListPopular?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.POPULAR, isError, defaultSelect), config)
            moviesListPopular = livePagedBuilder.build()
        }
    }

    private fun topRatedPagingFactory(defaultSelect: DefaultSelectListener){
        if (moviesListTopRated?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.TOP_RATED, isError, defaultSelect), config)
            moviesListTopRated = livePagedBuilder.build()
        }
    }

    private fun revenuePagingFactory(defaultSelect: DefaultSelectListener){
        if (moviesListRevenue?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.REVENUE, isError, defaultSelect), config)
            moviesListRevenue = livePagedBuilder.build()
        }
    }

    private fun releaseDatePagingFactory(defaultSelect: DefaultSelectListener){
        if (moviesListReleaseDate?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.RELEASE_DATE, isError, defaultSelect), config)
            moviesListReleaseDate = livePagedBuilder.build()
        }
    }
}
