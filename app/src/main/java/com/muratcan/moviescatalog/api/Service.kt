package com.muratcan.moviescatalog.api

import com.muratcan.moviescatalog.model.response.MoviesResponse
import retrofit2.http.*

/**
 * Created by MuratCan on 2019-10-17.
 */

interface Service {

    @GET("discover/movie")
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?,
        @Query("language") lang: String,
        @Query("include_adult") includeAdult: String?,
        @Query("include_video") includeVideo: String?,
        @Query("sort_by") sortBy: String?
    ): MoviesResponse

}