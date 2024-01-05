package com.nandits.goalscatcher.view

import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.NumberPicker
import com.nandits.goalscatcher.R
import com.nandits.goalscatcher.base.BaseActivity
import com.nandits.goalscatcher.data.InputTextConfig
import com.nandits.goalscatcher.databinding.ActivityMainBinding
import com.nandits.goalscatcher.utils.HawkKeys
import com.nandits.goalscatcher.utils.emptyString
import com.nandits.goalscatcher.utils.getHawkList
import com.nandits.goalscatcher.utils.setHawkList
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
        setTabYears()
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
                showYearPicker()
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
        val calendar = Calendar.getInstance()
        if (getHawkList(HawkKeys.SAVED_YEAR).orEmpty().isEmpty()) {
            setHawkList(HawkKeys.SAVED_YEAR, arrayListOf(calendar.get(Calendar.YEAR).toString()))
        }
        return getHawkList(HawkKeys.SAVED_YEAR).orEmpty().toMutableList()
    }

    private fun showYearPicker() {
        val calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_year_picker)
        val btnOk = dialog.findViewById<Button>(R.id.btnOk)
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancel)
        val yearPicker = dialog.findViewById<NumberPicker>(R.id.yearPicker)
        yearPicker.apply {
            minValue = year - 4
            maxValue = year
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            wrapSelectorWheel = false
            value = year
        }
        btnOk.setOnClickListener {
            val pickerValue = yearPicker.value.toString()
            val years = getHawkList(HawkKeys.SAVED_YEAR)

            if (years.orEmpty().contains(pickerValue).not()) {
                years?.add(pickerValue)
            }

            setHawkList(HawkKeys.SAVED_YEAR, years.orEmpty().sorted())
            setTabYears()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}