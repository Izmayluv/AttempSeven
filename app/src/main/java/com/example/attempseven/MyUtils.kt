package com.example.attempseven

import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyUtils {

    companion object{
        fun  loadImageFromUrl(url: String?, imageView: ImageView){
            GlideApp.with(imageView.context)
                .load(url)
                .error(R.drawable.img_not_available)
                .placeholder(R.drawable.img_placeholder)
                .fitCenter()
                .into(imageView)
        }
        fun toSimpleString(date: Date?) : String {
            val format = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru-ru"))
            return if (date is Date) format.format(date) else return "not date"
        }
    }
}
