package com.jessy.zoo

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jessy.zoo.network.LoadApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().build()
        Glide.with(imgView.context)
            .load(imgUri).centerCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.background)
                    .error(R.drawable.image_error)
            )
            .into(imgView)
    }

    @BindingAdapter("setupApiStatus")
    fun bindApiStatus(view: ProgressBar, status: LoadApiStatus?) {
        when (status) {
            LoadApiStatus.LOADING -> view.visibility = View.VISIBLE
            LoadApiStatus.DONE, LoadApiStatus.ERROR -> view.visibility = View.GONE
        }
    }

    @BindingAdapter("setupApiErrorMessage")
    fun bindApiErrorMessage(view: TextView, message: String?) {
        when (message) {
            null, "" -> {
                view.visibility = View.GONE
            }
            else -> {
                view.text = message
                view.visibility = View.VISIBLE
            }
        }
    }
}
