package com.nandits.goalscatcher.data

import android.os.Parcelable
import com.nandits.goalscatcher.utils.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputTextConfig(
    val title: String,
    val hint: String,
    val text: String = emptyString(),
    val isCloseable: Boolean = false,
    val info: String = emptyString(),
) : Parcelable
