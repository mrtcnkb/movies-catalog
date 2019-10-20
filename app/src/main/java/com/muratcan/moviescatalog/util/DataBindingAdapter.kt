package com.muratcan.moviescatalog.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.muratcan.moviescatalog.BuildConfig
import com.muratcan.moviescatalog.R

/**
 * Created by MuratCan on 2019-10-20.
 */

object DataBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadPhoto")
    fun loadImage(view: ImageView, photoUrl: String?) {
        if (photoUrl != null) view.loadImage(BuildConfig.IMAGE_BASE + photoUrl)
        else view.loadImage(R.drawable.banner_no_image_available)
    }

}