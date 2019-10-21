package com.muratcan.moviescatalog.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.muratcan.moviescatalog.datasource.MoviesDataSource
import com.muratcan.moviescatalog.model.ParameterTypeEnum
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.ui.listener.DefaultSelectListener

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesDataSourceFactory(private val type: ParameterTypeEnum, private val isError: MutableLiveData<Boolean>, private val defaultSelect: DefaultSelectListener) : DataSource.Factory<String, Result>()  {
    override fun create(): DataSource<String, Result> {
        return MoviesDataSource(type, isError, defaultSelect)
    }

}