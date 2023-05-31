package com.space.quizapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.space.quizapp.common.extensions.coroutines.collectAsync
import com.space.quizapp.common.extensions.coroutines.executeAsync
import com.space.quizapp.common.extensions.utils.makeSnackbar
import com.space.quizapp.common.extensions.utils.withBinding
import com.space.quizapp.common.util.QuizCustomThrowable
import com.space.quizapp.presentation.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.presentation.ui.common.navigation.QuizFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : QuizBaseViewModel> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract val vmc: KClass<VM>
    protected val vm: VM by viewModelForClass(clazz = vmc)

    abstract fun inflate(): Inflater<VB>
    abstract fun onBind()
    abstract fun setContent()

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
        setContent()
        onBind()
        observeNavigation()
        observeError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigate(directions: QuizFragmentDirections) {
        vm.navigate(directions)
    }

    private fun observeNavigation() {
        executeAsync {
            collectAsync(vm.navigationState) {
                it?.let {
                    findNavController().popBackStack()
                    findNavController().navigate(it.directions)
                }
            }
        }
    }

    private fun observeError() {
        collectAsync(vm.errorState) {
            it?.let {
                setError(it)
            }
        }
    }

    open fun setError(error: QuizCustomThrowable) {
        withBinding {
            error.errorResource?.let {
                binding.root.makeSnackbar(getString(it))
            }
            error.errorString?.let {
                binding.root.makeSnackbar(it)
            }
        }
    }
}
