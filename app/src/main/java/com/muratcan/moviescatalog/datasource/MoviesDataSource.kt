package com.muratcan.moviescatalog.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.muratcan.moviescatalog.MoviesCatalogApplication
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.model.ParameterTypeEnum
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.model.response.MoviesResponse
import com.muratcan.moviescatalog.repository.MoviesRepository
import com.muratcan.moviescatalog.ui.listener.DefaultSelectListener
import com.muratcan.moviescatalog.util.Config.isTablet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesDataSource(private val paramType: ParameterTypeEnum, private val isError: MutableLiveData<Boolean>, private val initialSelect: DefaultSelectListener) : PageKeyedDataSource<String, Result>(), KoinComponent{

    private val repo: MoviesRepository by inject()
    private val args = HashMap<String, String>()
    private val context = MoviesCatalogApplication.context

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Result>) {
        fetchInitialData(prepareParams("1"), callback)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Result>) {
        fetchAfterData(prepareParams(params.key), params, callback)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Result>) {}

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * İstek parametreleri hazırlanıyor.
     */
    private fun prepareHashMap(page: String): HashMap<String, String>{
        args["page"] = page
        args["include_adult"] = "false"
        args["include_video"] = "false"
        args["sort_by"] = when(paramType){
            ParameterTypeEnum.POPULAR -> "${context.resources?.getString(R.string.sort_popular)}"
            ParameterTypeEnum.TOP_RATED -> "${context.resources?.getString(R.string.sort_top_rated)}"
            ParameterTypeEnum.REVENUE -> "${context.resources?.getString(R.string.sort_revenue)}"
            ParameterTypeEnum.RELEASE_DATE -> "${context.resources?.getString(R.string.sort_release_date)}"
        }
        return args
    }

    fun prepareParams(key: String): HashMap<String, String>{
        return prepareHashMap(key)
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun fetchInitialData(hm: HashMap<String, String>, callback: LoadInitialCallback<String, Result>){
        GlobalScope.launch {
            repo.fetchMovies(hm).let {
                if (it?.results != null && it.results.isNotEmpty()){
                    successInitialFetch(it, callback)
                }else isError.postValue(true)
            }
        }
    }

    fun fetchAfterData(hm: HashMap<String, String>, params: LoadParams<String>, callback: LoadCallback<String, Result>){
        GlobalScope.launch {
            repo.fetchMovies(hm).let {
                if (it?.results != null && it.results.isNotEmpty())
                    successAfterFetch(it, params, callback)
                else isError.postValue(true)
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun successInitialFetch(response: MoviesResponse, callback: LoadInitialCallback<String, Result>){

        /**
         * Tablet görünümünde listelerimiz yüklendiğinde detay ekranına default olarak ilk itemi gönderiyoruz.
         */
        if (!response.results.isNullOrEmpty() && paramType == ParameterTypeEnum.POPULAR && isTablet)
            initialSelect.onItemLoaded(response.results[0])

        callback.onResult(response.results?: arrayListOf(), 0, response.total_results?:0, null, "2")
    }

    fun successAfterFetch(response: MoviesResponse, params: LoadParams<String>, callback: LoadCallback<String, Result>){
        callback.onResult(response.results ?:arrayListOf(), (params.key.toInt() + 1).toString())
    }

}