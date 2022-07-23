package com.mehmetkurt.techcareerfinal.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mehmetkurt.techcareerfinal.extension.loadImage

class FoodBinding {
    companion object {
        //https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png

        @BindingAdapter("load_image")
        @JvmStatic
        fun loadImage(imageView: ImageView, foodImage: String) {
            val imageUrl = "  http://kasimadalan.pe.hu/yemekler/resimler/$foodImage.png"
            imageView.loadImage(imageUrl)
        }
    }
}