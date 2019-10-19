package com.muratcan.moviescatalog.ui.listener

import android.view.View
import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-18.
 */

interface ItemClickListener {
    fun onItemClick(view: View, position: Int, selected: Result?)
}