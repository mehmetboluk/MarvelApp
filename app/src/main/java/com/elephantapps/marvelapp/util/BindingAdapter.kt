package com.elephantapps.marvelapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["loadImage"],requireAll = false)
    fun ImageView.loadImage(url:String?){
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}