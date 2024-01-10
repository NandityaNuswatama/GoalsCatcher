package com.nandits.goalscatcher.data

import android.os.Parcelable
import com.nandits.goalscatcher.utils.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Goal(
    val id: Int = 0,
    val year: String = emptyString(),
    val category: String = emptyString(),
    val isAchieved: Boolean = false,
    val goalTitle: String = emptyString(),
    val achievedDate: String = emptyString(),
    val photos: MutableList<String> = mutableListOf()
) : Parcelable
