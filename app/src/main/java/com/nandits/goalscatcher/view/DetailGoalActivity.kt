package com.nandits.goalscatcher.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.base.BaseActivity
import com.nandits.goalscatcher.data.Goal
import com.nandits.goalscatcher.databinding.ActivityDetailGoalBinding
import com.nandits.goalscatcher.utils.BundleKeys

class DetailGoalActivity : BaseActivity<ActivityDetailGoalBinding>() {

    companion object {
        fun getIntent(goal: Goal): Intent {
            return Intent().apply {
                putExtra(BundleKeys.GOAL, goal)
            }
        }
    }

    override fun getViewBinding(): ActivityDetailGoalBinding {
        return ActivityDetailGoalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}