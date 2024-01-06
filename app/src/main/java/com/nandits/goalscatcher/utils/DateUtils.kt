package com.nandits.goalscatcher.utils

import android.icu.util.Calendar

fun getCurrentYear(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR)
}