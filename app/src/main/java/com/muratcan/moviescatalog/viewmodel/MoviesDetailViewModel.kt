package com.muratcan.moviescatalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesDetailViewModel : ViewModel() {
    var movieObject = MutableLiveData<Result>()
}
