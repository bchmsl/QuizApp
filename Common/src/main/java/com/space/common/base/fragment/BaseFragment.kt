package com.space.common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.space.common.base.viewmodel.BaseViewModel
import com.space.common.dialog.AlertDialogView
import com.space.common.dialog.DialogFactory
import com.space.common.dialog.PromptDialogView
import com.space.common.extensions.coroutines.observeLiveDataNonNull
import com.space.common.extensions.utils.makeSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VB? = null

    val binding
        get() = _binding!!

    abstract val vmc: KClass<VM>
    protected val vm: VM by viewModelForClass(clazz = vmc)

    private val promptDialog by lazy {
        DialogFactory.createDialog(
            DialogFactory.Dialog.DIALOG_PROMPT,
            requireContext()
        ) as PromptDialogView.Builder
    }
    protected val alertDialog by lazy {
        DialogFactory.createDialog(
            DialogFactory.Dialog.DIALOG_ALERT,
            requireContext()
        ) as AlertDialogView.Builder
    }

    protected abstract fun inflate(): com.space.common.util.Inflater<VB>
    open fun onBind() {
        observe()
    }

    abstract fun observe()
    abstract fun onFragmentCreate()
    protected abstract fun setListeners()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFragmentCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflate().invoke(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        setListeners()
        observeError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observeError() {
        observeLiveDataNonNull(vm.errorState) {
            setError(it)
        }
    }

    open fun setError(error: com.space.common.util.CustomThrowable) {
        with(binding) {
            error.errorResource?.let {
                root.makeSnackBar(getString(it))
            }
            error.errorString?.let {
                root.makeSnackBar(it)
            }
        }
    }

    protected fun showPromptDialog(
        @StringRes message: Int,
        onPositiveButton: (() -> Unit)? = null,
        onNegativeButton: (() -> Unit)? = null
    ) {
        val dialog = promptDialog
            .setMessage(getString(message))
            .setPositiveButton(getString(com.space.common.util.S.yes)) {
                onPositiveButton?.invoke()
                it.dismiss()
            }.setNegativeButton(getString(com.space.common.util.S.no)) {
                onNegativeButton?.invoke()
                it.dismiss()
            }.build() as PromptDialogView
        dialog.show()
    }
}
