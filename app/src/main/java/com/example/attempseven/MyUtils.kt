package com.example.attempseven

import android.widget.ImageView

class MyUtils {

    companion object{
        fun  loadImageFromUrl(url: String, imageView: ImageView){
            GlideApp.with(imageView.context)
                .load(url)
                .error(R.drawable.img_not_available)
                .placeholder(R.drawable.img_placeholder)
                .fitCenter()
                .into(imageView)
        }
    }
}
