package com.muratcan.moviescatalog.model.data

import android.os.Parcelable
import com.muratcan.moviescatalog.MoviesCatalogApplication
import com.muratcan.moviescatalog.R
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by MuratCan on 2019-10-18.
 */

private val sdfTarget = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
private val sdfDefault = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

@Parcelize
data class Result(
    val id: Int,
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val genre_ids: ArrayList<Int>? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: String? = null,
    val vote_count: Int? = null
): Parcelable{
    fun getDescription(): String{
        return if (overview != null && overview != "") "\t\t$overview" else MoviesCatalogApplication.context.resources?.getString(R.string.no_desc_entered)?:"-"
    }

    fun getFormattedDate(): String{
        return if (release_date != null && release_date != "") sdfTarget.format(sdfDefault.parse(release_date)) else MoviesCatalogApplication.context.resources?.getString(R.string.no_date_entered)?:"-"
    }
}