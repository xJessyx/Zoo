package com.jessy.zoo

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
}
