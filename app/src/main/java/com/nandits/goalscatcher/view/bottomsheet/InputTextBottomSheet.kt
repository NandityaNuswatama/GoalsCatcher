package com.nandits.goalscatcher.view.bottomsheet

import android.os.Bundle
import com.nandits.goalscatcher.base.BaseBottomSheetFragment
import com.nandits.goalscatcher.data.InputTextConfig
import com.nandits.goalscatcher.databinding.BottomSheetInputTextBinding
import com.nandits.goalscatcher.utils.BundleKeys
import com.nandits.goalscatcher.utils.default
import com.nandits.goalscatcher.utils.onlyVisibleIf
import com.nandits.goalscatcher.utils.parcelable

class InputTextBottomSheet : BaseBottomSheetFragment<BottomSheetInputTextBinding>() {

    companion object {
        fun newInstance(
            config: InputTextConfig,
            doneListener: ((String) -> Unit)? = null,
            closeListener: (() -> Unit)? = null
        ): InputTextBottomSheet {
            val bundle = Bundle().apply { putParcelable(BundleKeys.INPUT_TEXT_CONFIG, config) }

            return InputTextBottomSheet().apply {
                arguments = bundle
                this.doneListener = doneListener
                this.closeListener = closeListener
            }
        }
    }

    override val tagName: String = InputTextBottomSheet::class.java.simpleName

    private var doneListener: ((String) -> Unit)? = null
    private var closeListener: (() -> Unit)? = null
    private var config: InputTextConfig? = null

    override fun initArgs() {
        config = arguments?.parcelable(BundleKeys.INPUT_TEXT_CONFIG)
    }

    override fun initUI() {
        with(binding) {
            tvTitle.text = config?.title
            btnClose.onlyVisibleIf(config?.isCloseable.default(false))
            cvInfo.onlyVisibleIf(config?.info.default().isNotEmpty())
            tvInfo.text = config?.info
            edtInput.hint = config?.hint
            edtInput.setText(config?.text)
        }
    }

    override fun initAction() {
        with(binding) {
            btnDone.setOnClickListener {
                doneListener?.invoke(edtInput.text.toString())
                dismiss()
            }

            btnClose.setOnClickListener {
                closeListener?.invoke()
                dismiss()
            }
        }
    }
}