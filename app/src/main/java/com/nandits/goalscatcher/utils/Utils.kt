package com.nandits.goalscatcher.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.hawk.Hawk

fun emptyString() = ""

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun setHawkList(key: String, list: List<String>) {
    val gson = Gson()
    val json = gson.toJson(list)
    Hawk.put(key, json)
}

fun getHawkList(key: String): ArrayList<String>? {
    val gson = Gson()
    val json = Hawk.get<String>(key)
    val type = object : TypeToken<ArrayList<String>>() {}.type
    return gson.fromJson(json, type)
}
