package com.nandits.goalscatcher.view

import android.os.Bundle
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.base.BaseActivity
import com.nandits.goalscatcher.data.InputTextConfig
import com.nandits.goalscatcher.databinding.ActivityMainBinding
import com.nandits.goalscatcher.utils.HawkKeys
import com.nandits.goalscatcher.utils.emptyString
import com.nandits.goalscatcher.utils.getCurrentYear
import com.nandits.goalscatcher.utils.getHawkList
import com.nandits.goalscatcher.utils.setHawkList
import com.nandits.goalscatcher.view.bottomsheet.InputTextBottomSheet
import com.nandits.goalscatcher.view.dialog.showYearPicker
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
        setTabYears()
        setTabWithText(getCurrentYear().toString())
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

            fabAddYear.setOnClickListener {
                showYearPicker(this@MainActivity) {
                    val years = getHawkList(HawkKeys.SAVED_YEAR)

                    if (years.orEmpty().contains(it).not()) {
                        years?.add(it)
                    }

                    setHawkList(HawkKeys.SAVED_YEAR, years.orEmpty().sorted())
                    setTabYears()
                    setTabWithText(it)
                }
            }

            btnAddCategory.setOnClickListener {
                InputTextBottomSheet.newInstance(
                    config = InputTextConfig(
                        title = getString(R.string.label_add_category),
                        hint = getString(R.string.label_enter_category_name),
                        isCloseable = false,
                        info = getString(R.string.message_info_add_category)
                    ),
                    doneListener = {
                        Hawk.put(HawkKeys.LAST_CATEGORY, it)
                        btnDeleteYear.text = Hawk.get(HawkKeys.LAST_CATEGORY)
                    },
                ).showBottomSheet(supportFragmentManager)
            }
            btnDeleteYear.text = Hawk.get(HawkKeys.LAST_CATEGORY)
            btnDeleteYear.setOnClickListener {

            }
        }
    }

    private fun setToolbarHome() {
        homeTitle = Hawk.get<String?>(HawkKeys.HOME_TITLE).orEmpty()
        binding.toolbarHome.apply {
            tvToolbar.text = getString(R.string.format_user_goals, homeTitle.ifEmpty { "Your Name" })
        }
    }

    private fun setTabYears() {
        with(binding.tabYear) {
            if (this.tabCount > 0) removeAllTabs()
            getSavedYear().sorted().forEach { addTab(newTab().setText(it)) }
        }
    }

    private fun getSavedYear(): MutableList<String> {
        if (getHawkList(HawkKeys.SAVED_YEAR).orEmpty().isEmpty()) {
            setHawkList(HawkKeys.SAVED_YEAR, arrayListOf(getCurrentYear().toString()))
        }
        return getHawkList(HawkKeys.SAVED_YEAR).orEmpty().toMutableList()
    }

    private fun setTabWithText(text: String) {
        with(binding.tabYear) {
            getTabAt(getSavedYear().indexOf(text))?.select()
        }
    }
}