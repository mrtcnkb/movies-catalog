package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
import com.muratcan.moviescatalog.ui.listener.ItemClickListener
import com.muratcan.moviescatalog.util.checkConnectivity
import com.muratcan.moviescatalog.util.getScreenWidth
import com.muratcan.moviescatalog.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesFragment : BaseFragment(), ItemClickListener {

    override fun onItemClick(view: View, position: Int, selected: Result?) {
        //Todo: navigation component arguman eklenip detay sayfasına yönlendirilecek.
    }

    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var dataBinding: MoviesFragmentBinding
    private lateinit var adapterPopular: MoviesListAdapter
    private lateinit var adapterTopRated: MoviesListAdapter
    private lateinit var adapterRevenue: MoviesListAdapter
    private var errMsg: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.moviesViewmodel = viewModel

        viewModel.preparePagingFactories()

        prepareListeners()
        prepareAdapter()

        setRootView(dataBinding.root)
        return rootBinding.root
    }

    private fun prepareListeners(){
        with(viewModel){
            isError.observe(this@MoviesFragment, Observer { it?.let { isErr ->
                if (isErr){
                    showErrorMessage()
                    isError.value = false
                }
            }})

            moviesListPopular?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterPopular.submitList(dataList)
            })

            moviesListTopRated?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterTopRated.submitList(dataList)
            })

            moviesListRevenue?.observe(this@MoviesFragment, Observer<PagedList<Result>> { dataList ->
                adapterRevenue.submitList(dataList)
            })
        }
    }

    private fun prepareAdapter(){
        with(dataBinding){
            recyclerviewPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewTopRated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerviewRevenue.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            adapterPopular = MoviesListAdapter(this@MoviesFragment)
            recyclerviewPopular.adapter = adapterPopular

            adapterTopRated = MoviesListAdapter(this@MoviesFragment)
            recyclerviewTopRated.adapter = adapterTopRated

            adapterRevenue = MoviesListAdapter(this@MoviesFragment)
            recyclerviewRevenue.adapter = adapterRevenue
        }
    }

    private fun showErrorMessage(){
        context?.let {
            errMsg = if (!checkConnectivity(it))
                it.resources?.getString(R.string.check_network)
            else
                it.resources?.getString(R.string.unexpected_error)
            errorDialog(errMsg)
        }
    }

}