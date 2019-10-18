package com.muratcan.moviescatalog.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muratcan.moviescatalog.MoviesCatalogApplication
import com.muratcan.moviescatalog.R
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by MuratCan on 17-10-2019.
 */


fun FragmentManager?.closeFragment(fr: Fragment){
    this?.beginTransaction()?.remove(fr)?.commit()
}

fun FragmentManager?.popBack(fr: Fragment) {
    this?.popBackStack(fr.javaClass.simpleName, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun FragmentManager?.replaceFragment(id: Int, fr: Fragment) {
    this?.let { fm->
        if (fm.fragments.indexOf(fr) == -1) {
            fm.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_out)
                .addToBackStack(fr.javaClass.simpleName)
                .replace(id, fr)
                .commit()
        }
    }
}

fun FragmentManager?.addFragment(id: Int, fr: Fragment) {
    this?.let { fm->
        if (fm.fragments.indexOf(fr) == -1) {
            fm.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_out, R.anim.fade_out)
                .addToBackStack(fr.javaClass.simpleName)
                .add(id, fr)
                .commit()
        }
    }
}

fun FragmentManager?.removeAllFragments() {
    this?.let { fm->
        fm.fragments.forEach {
            fm.popBackStackImmediate()
        }
    }
}

fun fromHtml(html: String?): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(html)
    }
}

fun ImageView.loadCircularImage(c: Context, uri: Int) {
    Glide.with(c)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
        ).apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun ImageView.loadImage(c: Context, uri: Any) {
    Glide.with(c)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
        ).into(this)
}

fun ImageView.loadImage(c: Context, uri: Int) {
    Glide.with(c)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
        ).into(this)
}

fun ImageView.loadImage(c: Context, uri: String, placeholder: Int) {
    Glide.with(c)
        .load(uri)
        .apply(
            RequestOptions()
                .placeholder(placeholder)
                .error(placeholder)
        ).into(this)
}

fun ImageView.clearImage(c: Context) {
    Glide.with(c).clear(this)
}

fun getMyDate(timestamp: String, format: String): String? {
    return try {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT+03:00")
        sdf.format(Date(timestamp.toLong() * 1000))
    } catch (e: Exception) {
        e.toString()
    }
}

fun hideKeyboard(activity: Activity) {
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
}

fun getScreenHeight(): Int {
    (MoviesCatalogApplication.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(DisplayMetrics())
    return DisplayMetrics().heightPixels
}

fun getScreenWidth(): Int{
    (MoviesCatalogApplication.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(DisplayMetrics())
    return DisplayMetrics().widthPixels
}