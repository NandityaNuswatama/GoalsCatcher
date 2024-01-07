package com.nandits.goalscatcher.data

import com.nandits.goalscatcher.utils.gsonFromJson
import com.nandits.goalscatcher.utils.gsonToJson

fun String.toGoal(): Goal {
    return this.gsonFromJson(Goal::class.java)?: Goal()
}

fun Goal.toJsonString(): String {
    return this.gsonToJson()
}