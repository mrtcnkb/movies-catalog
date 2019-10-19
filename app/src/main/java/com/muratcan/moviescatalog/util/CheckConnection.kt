package com.muratcan.moviescatalog.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by MuratCan on 2019-10-19.
 */

fun checkConnectivity(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    val isConnected = activeNetwork != null && activeNetwork.isConnected
    return isConnected
}