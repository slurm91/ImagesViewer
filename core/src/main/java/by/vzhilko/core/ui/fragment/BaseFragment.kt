package by.vzhilko.core.ui.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import by.vzhilko.core.di.annotation.IDIContainerProvider
import by.vzhilko.core.ui.navigation.INavigator
import javax.inject.Inject

abstract class BaseFragment<Component : Any> : Fragment() {

    lateinit var component: Component

    @Inject
    lateinit var navigator: INavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component = initAndGetComponent()
    }

    abstract fun initAndGetComponent(): Component

    fun getDIContainerProvider(): IDIContainerProvider {
        return requireActivity().application as IDIContainerProvider
    }

}