package com.muratcan.moviescatalog.repository

import com.muratcan.moviescatalog.BuildConfig
import com.muratcan.moviescatalog.api.Service
import com.muratcan.moviescatalog.model.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesRepository(private val retrofit: Service) {

    suspend fun fetchMovies(hm: HashMap<String, String>): MoviesResponse? {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext retrofit.fetchMovies(BuildConfig.API_KEY, hm["page"],"tr-TR", hm["include_adult"], hm["include_video"], hm["sort_by"])
            }catch (e: Exception){
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}