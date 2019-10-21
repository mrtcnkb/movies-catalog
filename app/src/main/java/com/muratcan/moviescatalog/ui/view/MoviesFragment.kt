package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.MoviesFragmentBinding
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.ui.adapter.MoviesListAdapter
import com.muratcan.moviescatalog.ui.listener.DefaultSelectListener
import com.muratcan.moviescatalog.ui.listener.ItemClickListener
import com.muratcan.moviescatalog.util.Config.isTablet
import com.muratcan.moviescatalog.util.checkConnectivity
import com.muratcan.moviescatalog.util.getScreenWidth
import com.muratcan.moviescatalog.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesFragment : BaseFragment(), ItemClickListener, DefaultSelectListener{

    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var dataBinding: MoviesFragmentBinding
    private lateinit var adapterPopular: MoviesListAdapter
    private lateinit var adapterTopRated: MoviesListAdapter
    private lateinit var adapterRevenue: MoviesListAdapter
    private lateinit var adapterReleaseDate: MoviesListAdapter
    private lateinit var navController: NavController
    private var errMsg: String? = ""
    private val bundle = Bundle()

    override fun onItemClick(view: View, position: Int, selected: Result?) {
        selected?.let { navigateMoviesDetailFragment(it) }
    }

    override fun onItemLoaded(selected: Result?) {
        selected?.let { navigateMoviesDetailFragment(it) }
    }

    private fun navigateMoviesDetailFragment(data: Result){
        if (isTablet){
            bundle.putParcelable("movieObject", data)
            navController.navigate(R.id.moviesDetailFragment2, bundle)
        }
        else
            navController.navigate(MoviesFragmentDirections.ActionMoviesFragmentToMoviesDetailFragment(data))
    }

    private fun setNavController(){
        navController = if (isTablet)
            (childFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment).navController
        else
            findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.moviesViewmodel = viewModel

        setNavController()
        if (!isTablet) setRecyclerviewHeight()

        /** PagingFactory builder */
        viewModel.preparePagingFactories(this)

        prepareListeners()
        prepareAdapter()

        setRootView(dataBinding.root)
        return rootBinding.root
    }

    private fun prepareListeners(){
        with(viewModel){
            isError.observe(this@MoviesFragment, Observer {
                if (it == true){
                    showErrorMessage()
                    isError.value = false
                }
            })

            moviesListPopular?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterPopular.submitList(dataList)
            })

            moviesListTopRated?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterTopRated.submitList(dataList)
            })

            moviesListRevenue?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterRevenue.submitList(dataList)
            })

            moviesListReleaseDate?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterReleaseDate.submitList(dataList)
            })
        }
    }

    private fun prepareAdapter(){
        with(dataBinding){
            recyclerviewPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewTopRated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewRevenue.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewReleaseDate.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            adapterPopular = MoviesListAdapter(this@MoviesFragment)
            recyclerviewPopular.adapter = adapterPopular

            adapterTopRated = MoviesListAdapter(this@MoviesFragment)
            recyclerviewTopRated.adapter = adapterTopRated

            adapterRevenue = MoviesListAdapter(this@MoviesFragment)
            recyclerviewRevenue.adapter = adapterRevenue

            adapterReleaseDate = MoviesListAdapter(this@MoviesFragment)
            recyclerviewReleaseDate.adapter = adapterReleaseDate
        }
    }

    /**
     * Hata durumunda mesaj veriliyor.
     */
    private fun showErrorMessage(){
        context?.let {
            errMsg = if (!checkConnectivity(it))
                it.resources?.getString(R.string.check_network)
            else
                it.resources?.getString(R.string.unexpected_error)
            errorDialog(errMsg)
        }
    }

    /**
     * rv boyutlandırma yapılıyor.
     */
    private fun setRecyclerviewHeight(){
        val params = dataBinding.recyclerviewPopular.layoutParams
        params?.height = (getScreenWidth() / 5) * 2
        params?.width = LinearLayout.LayoutParams.MATCH_PARENT
        with(dataBinding) {
            recyclerviewPopular?.layoutParams = params
            recyclerviewTopRated?.layoutParams = params
            recyclerviewRevenue?.layoutParams = params
            recyclerviewReleaseDate?.layoutParams = params
        }
    }

}