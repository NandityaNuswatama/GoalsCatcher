package com.nandits.goalscatcher.data

import com.nandits.goalscatcher.utils.emptyString

data class Goal(
    val id: Int = 0,
    val year: String = emptyString(),
    val category: String = emptyString(),
    val isAchieved: Boolean = false,
    val goalTitle: String = emptyString(),
    val achievedDate: String = emptyString(),
    val photos: String = emptyString()
)
