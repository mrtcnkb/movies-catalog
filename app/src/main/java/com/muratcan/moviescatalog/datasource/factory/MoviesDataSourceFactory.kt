package com.muratcan.moviescatalog.datasource.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.muratcan.moviescatalog.datasource.MoviesDataSource
import com.muratcan.moviescatalog.model.ParameterTypeEnum
import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesDataSourceFactory(val type: ParameterTypeEnum, val isError: MutableLiveData<Boolean>) : DataSource.Factory<String, Result>()  {
    override fun create(): DataSource<String, Result> {
        return MoviesDataSource(type, isError)
    }

}