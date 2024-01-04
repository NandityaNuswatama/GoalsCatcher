package com.nandits.goalscatcher.view

import android.os.Bundle
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.base.BaseActivity
import com.nandits.goalscatcher.data.InputTextConfig
import com.nandits.goalscatcher.databinding.ActivityMainBinding
import com.nandits.goalscatcher.utils.HawkKeys
import com.nandits.goalscatcher.utils.emptyString
import com.nandits.goalscatcher.view.bottomsheet.InputTextBottomSheet
import com.orhanobut.hawk.Hawk

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var homeTitle = emptyString()
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        initAction()
    }

    private fun initUi() {
        setToolbarHome()
    }

    private fun initAction() {
        with(binding) {
            toolbarHome.tvToolbar.setOnClickListener {
                InputTextBottomSheet.newInstance(
                    config = InputTextConfig(
                        title = getString(R.string.label_enter_your_name),
                        hint = getString(R.string.label_enter_your_name),
                        text = homeTitle.ifEmpty { emptyString() },
                        isCloseable = homeTitle.isNotEmpty()
                    ),
                    doneListener = {
                        Hawk.put(HawkKeys.HOME_TITLE, it)
                        setToolbarHome()
                    }
                ).showBottomSheet(supportFragmentManager)
            }
        }
    }

    private fun setToolbarHome() {
        homeTitle = Hawk.get<String?>(HawkKeys.HOME_TITLE).orEmpty()
        binding.toolbarHome.apply {
            tvToolbar.text = getString(R.string.format_user_goals, homeTitle.ifEmpty { "Your Name" })
        }
    }
}