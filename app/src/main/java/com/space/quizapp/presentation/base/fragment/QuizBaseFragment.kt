package com.space.quizapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.extensions.coroutines.observeLiveDataNonNull
import com.space.quizapp.common.extensions.utils.makeSnackBar
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.Inflater
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.common.util.S
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import com.space.quizapp.presentation.ui.common.view.dialog.QuizAlertDialogView
import com.space.quizapp.presentation.ui.common.view.dialog.QuizDialogFactory
import com.space.quizapp.presentation.ui.common.view.dialog.QuizPromptDialogView
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

abstract class QuizBaseFragment<VB : ViewBinding, VM : QuizBaseViewModel> : Fragment() {

    private var _binding: VB? = null

    val binding
        get() = _binding!!

    abstract val vmc: KClass<VM>
    protected val vm: VM by viewModelForClass(clazz = vmc)

    private val promptDialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_PROMPT,
            requireContext()
        ) as QuizPromptDialogView.Builder
    }
    protected val alertDialog by lazy {
        QuizDialogFactory.createDialog(
            QuizDialogFactory.Dialog.DIALOG_ALERT,
            requireContext()
        ) as QuizAlertDialogView.Builder
    }

    protected abstract fun inflate(): Inflater<VB>
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
        observeNavigation()
        observeError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigate(directions: QuizFragmentDirections) {
        vm.navigate(directions)
    }

    private fun observeNavigation() {
        executeAsync {
            collectAsync(vm.navigationState) {
                it?.let {
                    with(findNavController()) {
                        popBackStack()
                        navigate(it.directions)
                    }
                }
            }
        }
    }

    private fun observeError() {
        observeLiveDataNonNull(vm.errorState) {
            setError(it)
        }
    }

    open fun setError(error: QuizCustomThrowable) {
        withBinding {
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
            .setPositiveButton(getString(S.yes)) {
                onPositiveButton?.invoke()
                it.dismiss()
            }.setNegativeButton(getString(S.no)) {
                onNegativeButton?.invoke()
                it.dismiss()
            }.build() as QuizPromptDialogView
        dialog.show()
    }
}
