package com.davenet.marsrealestate

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.davenet.marsrealestate.network.MarsProperty
import com.davenet.marsrealestate.overview.MarsApiStatus
import com.davenet.marsrealestate.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindingImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        GlideApp.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.apply {
                visibility = View.VISIBLE
                setImageResource(R.drawable.loading_animation)
            }
        }
        MarsApiStatus.ERROR -> {
            statusImageView.apply {
                visibility = View.VISIBLE
                setImageResource(R.drawable.ic_connection_error)
            }
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}