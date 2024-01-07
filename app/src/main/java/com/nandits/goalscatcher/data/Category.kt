package com.nandits.goalscatcher.data

import android.os.Parcelable
import com.nandits.goalscatcher.utils.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int = 0,
    val year: String = emptyString(),
    val categoryTitle: String = emptyString(),
    val goals: List<String> = emptyList(),
    val totalAchieved: Int = 0
) : Parcelable
