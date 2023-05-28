package by.vzhilko.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import by.vzhilko.core.ui.navigation.INavigator
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel, Binding : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: VM
    private var _binding: Binding? = null
    val binding: Binding get() = _binding!!

    //TODO::move to ViewModel
    @Inject
    lateinit var navigator: INavigator

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

    abstract fun initAndGetView(inflater: LayoutInflater, container: ViewGroup?): Binding

}