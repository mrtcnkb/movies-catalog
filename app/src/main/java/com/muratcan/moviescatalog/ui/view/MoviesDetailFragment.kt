package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.MoviesDetailFragmentBinding
import com.muratcan.moviescatalog.ui.view.MoviesDetailFragmentArgs.fromBundle
import com.muratcan.moviescatalog.viewmodel.MoviesDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 2019-10-18.
 */

class MoviesDetailFragment : BaseFragment() {

    private val viewModel: MoviesDetailViewModel by viewModel()
    private lateinit var dataBinding: MoviesDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movies_detail_fragment, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.movieDetailViewmodel = viewModel

        arguments?.let { viewModel.movieObject.value = fromBundle(it).movieObject }

        setRootView(dataBinding.root)
        return rootBinding.root
    }


}
