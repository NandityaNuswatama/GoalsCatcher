package com.nandits.goalscatcher.utils

object DialogStateController {

    private var mTags = mutableListOf<String>()

    @JvmStatic
    fun add(tag: String) {
        mTags.add(tag)
    }

    @JvmStatic
    fun remove(tag: String) {
        mTags.remove(tag)
    }

    @JvmStatic
    fun isShown(tag: String): Boolean {
        return mTags.find { it == tag } != null
    }

}