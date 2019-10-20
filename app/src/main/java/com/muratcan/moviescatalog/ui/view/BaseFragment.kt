package com.muratcan.moviescatalog.ui.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.databinding.BaseFragmentBinding
import com.muratcan.moviescatalog.util.checkConnectivity
import com.muratcan.moviescatalog.viewmodel.BaseViewModel
import hari.bounceview.BounceView
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by MuratCan on 2019-10-19.
 */

open class BaseFragment : Fragment() {

    protected lateinit var rootBinding: BaseFragmentBinding
    private val rootViewModel: BaseViewModel by viewModel()
    private lateinit var dialog: AlertDialog
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootBinding = BaseFragmentBinding.inflate(inflater, container, false)
        rootBinding.baseViewmodel = rootViewModel
        checkConnection()
        return rootBinding.root
    }

    protected fun setRootView(v: View){
        rootBinding.fragmentBase.addView(v)
    }

    fun checkConnection(){
        context?.let {
            if (!checkConnectivity(it)) errorDialog(it.resources?.getString(R.string.check_network))
        }
    }

    fun errorDialog(message: String?) {
        context?.let {
            if (rootViewModel.dialogIsAppear.value != true){
                rootViewModel.dialogIsAppear.value = true
                builder = AlertDialog.Builder(it)
                builder.setIcon(ContextCompat.getDrawable(it, R.drawable.ic_warning))
                builder.setTitle(it.resources.getString(R.string.went_wrong))
                builder.setMessage(message)
                val dialogClickListener = DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            rootViewModel.dialogIsAppear.value = false
                        }
                    }
                }
                builder.setPositiveButton(it.resources?.getString(R.string.okay), dialogClickListener)
                dialog = builder.create()
                BounceView.addAnimTo(dialog)
                dialog.setCancelable(false)
                dialog.show()
            }
        }
    }

}
