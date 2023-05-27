package com.space.quizapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.space.quizapp.common.extensions.collectAsync
import com.space.quizapp.common.extensions.executeAsync
import com.space.quizapp.common.extensions.makeSnackbar
import com.space.quizapp.common.extensions.withBinding
import com.space.quizapp.presentation.base.viewmodel.BaseViewModel
import com.space.quizapp.presentation.ui.navigation.FragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun getViewModelClass(): KClass<VM>

    private val vmc get() = getViewModelClass()
    protected val vm: VM by viewModelForClass(clazz = vmc)

    abstract fun inflate(): Inflater<VB>
    abstract fun onBind()

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
        observeNavigation()
        observeError()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigate(directions: FragmentDirections) {
        vm.navigate(directions)
    }

    private fun observeNavigation() {
        executeAsync {
            collectAsync(vm.navigationState) {
                it?.let {
                    findNavController().navigate(it.directions)
                }
            }
        }
    }

    private fun observeError() {
        executeAsync {
            collectAsync(vm.errorState) {
                it?.let {
                    setError(it)
                }
            }
        }
    }

    open fun setError(error: Any) {
        withBinding {
            binding.root.makeSnackbar(
                when (error) {
                    is String -> error
                    is Int -> getString(error)
                    else -> {
                        ""
                    }
                }
            )
        }
    }
}
