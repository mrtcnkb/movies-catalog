package com.muratcan.moviescatalog.model.response

import com.muratcan.moviescatalog.model.data.Result

/**
 * Created by MuratCan on 2019-10-18.
 */

data class MoviesResponse(
    val success: Int? = null,
    val status_code: Int? = null,
    val status_message: String? = null,
    val page: Int? = null,
    val results: ArrayList<Result>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)