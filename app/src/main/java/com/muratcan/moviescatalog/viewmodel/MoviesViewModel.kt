package com.muratcan.moviescatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.muratcan.moviescatalog.datasource.factory.MoviesDataSourceFactory
import com.muratcan.moviescatalog.model.ParameterTypeEnum
import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesViewModel : ViewModel() {

    var isError = MutableLiveData<Boolean>()

    var moviesListPopular: LiveData<PagedList<Result>>? = null
    var moviesListTopRated: LiveData<PagedList<Result>>? = null
    var moviesListRevenue: LiveData<PagedList<Result>>? = null
    var moviesListReleaseDate: LiveData<PagedList<Result>>? = null

    fun preparePagingFactories(){
        popularPagingFactory()
        topRatedPagingFactory()
        revenuePagingFactory()
        releaseDatePagingFactory()
    }

    private fun popularPagingFactory(){
        if (moviesListPopular?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.POPULAR, isError), config)
            moviesListPopular = livePagedBuilder.build()
        }
    }

    private fun topRatedPagingFactory(){
        if (moviesListTopRated?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.TOP_RATED, isError), config)
            moviesListTopRated = livePagedBuilder.build()
        }
    }

    private fun revenuePagingFactory(){
        if (moviesListRevenue?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.REVENUE, isError), config)
            moviesListRevenue = livePagedBuilder.build()
        }
    }

    private fun releaseDatePagingFactory(){
        if (moviesListReleaseDate?.value == null){
            val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
            val livePagedBuilder = LivePagedListBuilder<String, Result>(MoviesDataSourceFactory(ParameterTypeEnum.RELEASE_DATE, isError), config)
            moviesListReleaseDate = livePagedBuilder.build()
        }
    }
}
