package com.muratcan.moviescatalog.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.ActivitySingleBinding
import com.muratcan.moviescatalog.viewmodel.ActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by MuratCan on 17-10-2019.
 */

class SingleActivity : AppCompatActivity() {

    private lateinit var mBind: ActivitySingleBinding
    private val model: ActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind = DataBindingUtil.setContentView(this,
            R.layout.activity_single
        )
        mBind.lifecycleOwner = this@SingleActivity
        mBind.activityViewmodel = model

    }
}
