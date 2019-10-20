package com.muratcan.moviescatalog.ui.adapter

import android.graphics.drawable.Drawable
import android.util.Log
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
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.muratcan.moviescatalog.BuildConfig
import com.muratcan.moviescatalog.R
import com.muratcan.moviescatalog.model.data.Result
import com.muratcan.moviescatalog.ui.listener.ItemClickListener
import com.muratcan.moviescatalog.util.getScreenWidth
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
        var loading: LottieAnimationView? = null
        var photo: ImageView? = null

        init {
            frame = itemView.findViewById(R.id.frame) as FrameLayout
            loading = itemView.findViewById(R.id.lottie_loading) as LottieAnimationView
            photo = itemView.findViewById(R.id.photo) as ImageView
        }

        fun setData(item: Result?){
            photo?.let {
                /**
                 * Resim yüklenebilirse lottie loading animasyonunun visibility'sini GONE yapmak için listener ile dinliyoruz.
                 */
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_BASE + item?.poster_path)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_transparent)
                            .error(R.drawable.no_photo_available))
                    .listener(object: RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            loading?.visibility = View.VISIBLE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            loading?.visibility = View.GONE
                            return false
                        }

                    })
                    .into(it)
            }

            frame?.setOnClickListener {
                clickListener.onItemClick(it, adapterPosition, item)
            }

            calculateRowHeightAndWidth(frame)
        }

    }

    /**
     * Ekranda 3 tam, 1 yarım item görünmesi için her itemin genişliğini tekrar set ediyoruz.
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