package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.MoviesDetailFragmentBinding
import com.muratcan.moviescatalog.viewmodel.MoviesDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 18-10-2019.
 */

class MoviesDetailFragment : Fragment() {

    private val viewModel: MoviesDetailViewModel by viewModel()
    private lateinit var dataBinding: MoviesDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movies_detail_fragment, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.moviesDetailViewmodel = viewModel

        return dataBinding.root
    }

}
