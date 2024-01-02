package com.nandits.goalscatcher.di

import android.app.Application
import com.orhanobut.hawk.Hawk

class GoalsCatcherApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()
    }
}