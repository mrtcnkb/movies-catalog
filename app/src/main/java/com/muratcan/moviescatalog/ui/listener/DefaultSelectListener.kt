package com.muratcan.moviescatalog.ui.listener

import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-21.
 */

interface DefaultSelectListener {
    fun onItemLoaded(selected: Result?)
}