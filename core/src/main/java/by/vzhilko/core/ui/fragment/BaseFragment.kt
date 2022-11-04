package by.vzhilko.core.ui.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import by.vzhilko.core.di.IDIContainerProvider
import by.vzhilko.core.ui.navigation.INavigator
import by.vzhilko.core.ui.viewmodel.ViewModelFactory
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import javax.inject.Inject

abstract class BaseFragment<Component : Any, VM : ViewModel> : Fragment() {

    lateinit var component: Component
    lateinit var viewModel: VM

    @Inject
    lateinit var navigator: INavigator

    override fun onAttach(context: Context) {
        component = initAndGetComponent()
        viewModel = initAndGetViewModel()
        super.onAttach(context)
    }

    abstract fun initAndGetComponent(): Component

    abstract fun initAndGetViewModel(): VM

    protected fun getViewModelFactory(): ViewModelFactory {
        return (component as? ViewModelFactoryProvider)?.getViewModelFactory()
            ?: throw NullPointerException("ViewModelFactory instance is not introduced.")
    }

    fun getDIContainerProvider(): IDIContainerProvider {
        return requireActivity().application as IDIContainerProvider
    }

}