package com.nandits.goalscatcher.data

import com.nandits.goalscatcher.utils.emptyString

data class Category(
    val id: Int = 0,
    val year: String = emptyString(),
    val categoryTitle: String = emptyString(),
    val goals: String = emptyString()
)
