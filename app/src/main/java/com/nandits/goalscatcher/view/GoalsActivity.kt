package com.nandits.goalscatcher.view

import android.content.Intent
import android.os.Bundle
import com.nandits.goalscatcher.base.BaseActivity
import com.nandits.goalscatcher.data.Category
import com.nandits.goalscatcher.data.Goal
import com.nandits.goalscatcher.databinding.ActivityGoalsBinding
import com.nandits.goalscatcher.utils.BundleKeys

class GoalsActivity : BaseActivity<ActivityGoalsBinding>() {

    companion object {
        fun getIntent(category: Category): Intent {
            return Intent().apply {
                putExtra(BundleKeys.CATEGORY, category)
            }
        }
    }

    override fun getViewBinding(): ActivityGoalsBinding {
        return ActivityGoalsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}