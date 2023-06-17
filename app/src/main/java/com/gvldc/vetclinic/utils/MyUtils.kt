package com.gvldc.vetclinic.utils

import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.gvldc.vetclinic.GlideApp
import com.gvldc.vetclinic.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID

class MyUtils {

    companion object {
        fun loadImageFromUrlFit(url: String?, imageView: ImageView) {
            GlideApp.with(imageView.context)
                .load(url)
                .error(R.drawable.img_not_available)
                .placeholder(R.drawable.img_placeholder)
                .fitCenter()
                .into(imageView)
        }

        fun loadImageFromUrl(url: String?, imageView: ImageView) {
            GlideApp.with(imageView.context)
                .load(url)
                .error(R.drawable.img_not_available)
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .into(imageView)
        }

        fun generateUUID(): String {
            return UUID.randomUUID().toString()
        }

        fun stringToTimestamp(dateString: String, pattern: String): Timestamp? {
            val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date: Date? = try {
                dateFormat.parse(dateString)
            } catch (e: Exception) {
                null
            }
            return if (date != null) {
                Timestamp(date)
            } else {
                null
            }
        }

        fun timestampToString(timestamp: Timestamp, pattern: String): String {
            val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val date = timestamp.toDate()
            return dateFormat.format(date)
        }

/*        @RequiresApi(Build.VERSION_CODES.O)
        fun currentAge(date: Date?): String {
            val yearsOld = ChronoUnit.YEARS.between(
                date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate(),
                LocalDateTime.now()
            )

            return yearsOld.toString() + " " + addStringYearEnding(yearsOld)
        }*/

        private fun addStringYearEnding(time: Int): String {
            return when (time % 10) {
                1 -> "год"
                2, 3, 4 -> "года"
                0, 5, 6, 7, 8, 9 -> "лет"
                else -> "года"
            }
        }

        fun calculateAge(timestamp: Timestamp): String {
            val currentDate = Calendar.getInstance().time
            val birthDate = timestamp.toDate()

            val currentCalendar = Calendar.getInstance()
            val birthCalendar = Calendar.getInstance()
            currentCalendar.time = currentDate
            birthCalendar.time = birthDate

            var ageInYears = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)
            if (currentCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                (currentCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                        currentCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))
            ) {
                ageInYears--
            }

            return "$ageInYears ${addStringYearEnding(ageInYears)}"
        }

        fun dateToString(date: Date?): String {
            val format = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru-ru"))
            return if (date is Date) format.format(date) else return "not date"
        }
    }
}
