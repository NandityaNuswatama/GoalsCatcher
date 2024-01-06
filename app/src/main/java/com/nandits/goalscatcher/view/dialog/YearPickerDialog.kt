package com.nandits.goalscatcher.view.dialog

import android.app.Dialog
import android.content.Context
import android.icu.util.Calendar
import android.view.Window
import android.widget.Button
import android.widget.NumberPicker
import com.nandits.goalscatcher.R

fun showYearPicker(context: Context, onYearSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val dialog = Dialog(context)
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
        onYearSelected.invoke(pickerValue)

        dialog.dismiss()
    }
    btnCancel.setOnClickListener { dialog.dismiss() }
    dialog.show()
}