package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.MoviesFragmentBinding
import com.muratcan.moviescatalog.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 18-10-2019.
 */

class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModel()
    private lateinit var dataBinding: MoviesFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.moviesViewmodel = viewModel

        dataBinding.navigate.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_moviesDetailFragment)
        }

        return dataBinding.root
    }

}