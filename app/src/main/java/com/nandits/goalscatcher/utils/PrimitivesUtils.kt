package com.nandits.goalscatcher.utils

fun Boolean?.default(boolean: Boolean) = this ?: boolean

fun String?.default(string: String = emptyString()) = this ?: string

fun Double?.default(double: Double = 0.0) = this ?: double

fun Int?.default(int: Int = 0) = this ?: int

fun Long?.default(long: Long = 0) = this ?: long