package com.muratcan.moviescatalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by MuratCan on 2019-10-19.
 */

class BaseViewModel : ViewModel() {
    var dialogIsAppear = MutableLiveData<Boolean>()
}
