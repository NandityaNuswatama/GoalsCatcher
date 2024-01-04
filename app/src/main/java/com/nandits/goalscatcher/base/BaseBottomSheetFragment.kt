package com.nandits.goalscatcher.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nandits.goalscatcher.utils.DialogStateController
import java.lang.reflect.ParameterizedType

abstract class BaseBottomSheetFragment<binding : ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: binding? = null
    val binding: binding get() = _binding!!

    abstract val tagName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        _binding = method.invoke(null, layoutInflater, container, false) as binding
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.apply {
            setOnShowListener {
                val bottomSheet = this.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                if (bottomSheet != null) {
                    bottomSheet.background = null
                }
            }
        }

        initArgs()
        initUI()
        initAction()
    }

    abstract fun initArgs()

    abstract fun initUI()

    abstract fun initAction()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showBottomSheet(fragmentManager: FragmentManager) {
        if (!isAdded && !DialogStateController.isShown(tagName)) {
            DialogStateController.add(tagName)
            this.show(fragmentManager, tagName)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        DialogStateController.remove(tagName)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        DialogStateController.remove(tagName)
    }
}