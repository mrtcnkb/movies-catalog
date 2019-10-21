package com.muratcan.moviescatalog.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muratcan.moviescatalog.MoviesCatalogApplication
import com.muratcan.moviescatalog.R

/**
 * Created by MuratCan on 2019-10-17.
 */

fun ImageView.loadImage(uri: Any) {
    Glide.with(this.context)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_transparent)
                .error(R.drawable.no_photo_available)
        ).into(this)
}

fun getScreenHeight(): Int {
    val windowManager: WindowManager = MoviesCatalogApplication.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.heightPixels
}

fun getScreenWidth(): Int{
    val windowManager: WindowManager = MoviesCatalogApplication.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(dm)
    return dm.widthPixels
}