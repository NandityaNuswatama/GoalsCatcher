package com.nandits.goalscatcher.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

fun <T> String.gsonFromJson(clazz: Class<T>): T? {
    return try {
        Gson().fromJson(this, clazz)
    } catch (e: JsonSyntaxException) {
        null
    }
}

fun <T> String.listGsonFromJson(clazz: Class<T>): List<T> {
    val listType = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(this, listType)
}

fun Any?.gsonToJson(): String {
    return Gson().toJson(this)
}