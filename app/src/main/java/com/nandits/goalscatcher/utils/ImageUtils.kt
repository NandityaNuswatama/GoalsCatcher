package com.nandits.goalscatcher.utils

import android.widget.ImageView
import coil.load
import java.io.File

fun ImageView.loadFileImage(path: String) {
    load(File(path))
}