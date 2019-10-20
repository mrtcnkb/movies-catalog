package com.muratcan.moviescatalog.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.muratcan.moviescatalog.BuildConfig
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.MoviesDetailFragmentBinding
import com.muratcan.moviescatalog.ui.view.MoviesDetailFragmentArgs.fromBundle
import com.muratcan.moviescatalog.util.fromHtml
import com.muratcan.moviescatalog.util.loadImage
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
        dataBinding.moviesDetailViewmodel = viewModel

        viewModel.movieObject.value = arguments?.let { fromBundle(it).movieObject }


        /**
         * Databinding ile direkt view'a gömülecek. Şuan için böyle kaldı.
         */
        with(dataBinding){
            viewModel.movieObject.value?.let {
                title.text = it.title
                rate.text = it.getRate()
                backdrop?.loadImage(context!!, BuildConfig.IMAGE_BASE + it.backdrop_path)
                releaseDate.text = dateReformatter(it.release_date)
                description.text = it.getDescription()
            }
        }

        setRootView(dataBinding.root)
        return rootBinding.root
    }


}
