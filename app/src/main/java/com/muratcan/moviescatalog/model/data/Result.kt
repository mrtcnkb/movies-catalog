package com.muratcan.moviescatalog.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by MuratCan on 2019-10-18.
 */

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: ArrayList<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Parcelable{
    fun getDescription(): String{
        return "\t\t$overview"
    }

    fun getRate(): String{
        return vote_average.toString()
    }
}