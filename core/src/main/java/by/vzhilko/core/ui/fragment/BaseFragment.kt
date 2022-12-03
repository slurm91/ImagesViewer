package by.vzhilko.core.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import by.vzhilko.core.di.IDIContainerProvider
import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactoryProvider
import by.vzhilko.core.ui.viewmodel.ViewModelFactory
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import javax.inject.Inject

abstract class BaseFragment<Component : Any, VM : ViewModel, Binding : ViewDataBinding> : Fragment() {

    lateinit var component: Component
    lateinit var viewModel: VM
    private var _binding: Binding? = null
    val binding: Binding get() = _binding!!

    //TODO::move to ViewModel
    @Inject
    lateinit var navigator: INavigator

    override fun onAttach(context: Context) {
        component = initAndGetComponent()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = initAndGetViewModel(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initAndGetView(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun initAndGetComponent(): Component

    abstract fun initAndGetViewModel(savedInstanceState: Bundle? = null): VM

    abstract fun initAndGetView(inflater: LayoutInflater, container: ViewGroup?): Binding

    protected fun getViewModelFactory(): ViewModelFactory {
        return (component as? ViewModelFactoryProvider)?.getViewModelFactory()
            ?: throw NullPointerException("ViewModelFactory instance is not introduced.")
    }

    protected fun getAbstractSavedStateViewModelFactory(savedInstanceState: Bundle?): AbstractSavedStateViewModelFactory {
        return (component as? SavedStateViewModelFactoryProvider)
                ?.getSavedStateViewModelFactory()
                ?.create(this, savedInstanceState)
                ?: throw NullPointerException("AbstractSavedStateViewModelFactory instance is not introduced.")
    }

    fun getDIContainerProvider(): IDIContainerProvider {
        return requireActivity().application as IDIContainerProvider
    }

}