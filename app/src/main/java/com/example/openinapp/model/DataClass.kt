package com.example.openinapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ClickData(val created_at: String, val total_clicks: Float) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonth(): String {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(created_at, formatter)
        return dateTime.month.toString()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertToMonthlyClickData(data: List<ClickData>): List<ClickData> {
    return data.map { ClickData(it.getMonth(), it.total_clicks) }
}
