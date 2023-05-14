package com.example.attempseven

import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
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

        @RequiresApi(Build.VERSION_CODES.O)
        fun currentAge(date: Date?): String {
            val yearsOld = ChronoUnit.YEARS.between(
                date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate(),
                LocalDateTime.now()
            )

            return yearsOld.toString() + " " + addStringYearEnding(yearsOld)
        }

        private fun addStringYearEnding(time: Long): String{
            val lastDigit = time % 10
            return when (lastDigit.toInt()){
                1 -> "год"
                2,3,4 -> "года"
                0,5,6,7,8,9 -> "лет"
                else -> "года"
            }
        }
        fun dateToString(date: Date?): String {
            val format = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru-ru"))
            return if (date is Date) format.format(date) else return "not date"
        }
    }
}
