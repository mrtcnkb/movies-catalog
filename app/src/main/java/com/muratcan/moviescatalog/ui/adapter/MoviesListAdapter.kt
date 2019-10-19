package com.muratcan.moviescatalog.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muratcan.moviescatalog.BuildConfig
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.ui.listener.ItemClickListener
import com.muratcan.moviescatalog.util.getScreenWidth
import com.muratcan.moviescatalog.util.loadImage
import hari.bounceview.BounceView

/**
 * Created by MuratCan on 2019-10-18.
 */
class MoviesListAdapter(
    private val clickListener: ItemClickListener
) : PagedListAdapter<Result, MoviesListAdapter.ViewHolder>(diffUtilCallBack) {

    private var lastPosition = -1
    private var mColumn: Int = 3
    private val width: Int = getScreenWidth()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_movies, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position))
        BounceView.addAnimTo(holder.frame).setScaleForPopOutAnim(1f, 0f)
        setAnimation(holder.itemView, position)
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.fade_in)
            animation.duration = 350
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var frame: FrameLayout? = null
        var photo: ImageView? = null

        init {
            frame = itemView.findViewById(R.id.frame) as FrameLayout
            photo = itemView.findViewById(R.id.photo) as ImageView
        }

        fun setData(item: Result?){
            photo?.loadImage(itemView.context, BuildConfig.IMAGE_BASE + item?.poster_path)
            frame?.setOnClickListener {
                clickListener.onItemClick(it, adapterPosition, item)
            }
            calculateRowHeightAndWidth(frame)
        }

    }

    /**
     * Ekranda 3 tam, 1 yarım item görünmesi için her itemin genişliğini tekrar set ediyoruz.
     * Todo : Glide bitmap onResourceReady içerisinde bitmap ratio hesaplanıp hatasız bir genişlik hesaplama yapılacak
     */
    fun calculateRowHeightAndWidth(v: View?) {
        val params = v?.layoutParams
        params?.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        params?.width = ((width - 180) / mColumn)
        v?.layoutParams = params
    }

    companion object {
        private val diffUtilCallBack =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.title == newItem.title && oldItem.id == newItem.id
                }
            }
    }
}