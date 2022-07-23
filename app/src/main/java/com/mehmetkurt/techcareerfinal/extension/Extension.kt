package com.mehmetkurt.techcareerfinal.extension

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mehmetkurt.techcareerfinal.R
import androidx.navigation.fragment.findNavController

fun ImageView.loadImage(url: String?) {
    val placeholder = createPlaceHolder(this.context)
    this.load(url) {
        crossfade(true)
        crossfade(500)
        placeholder(placeholder)
    }
}

private fun createPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 12f
        centerRadius = 40f
        start()
    }
}
@BindingAdapter("android:downloadurl")
fun downloadImage(view: ImageView, url:String?){
    val urls = "http://kasimadalan.pe.hu/yemekler/resimler/${url}"
    view.downloadFromUrl(urls, createPlaceHolder(view.context))
}

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
inline fun Fragment.navigateToNextFragment(@IdRes id: Int, block: Bundle.() -> Unit = {}) {
    findNavController().navigate(id, Bundle().apply(block))
}