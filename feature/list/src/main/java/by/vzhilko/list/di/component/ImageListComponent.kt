package by.vzhilko.list.di.component

import by.vzhilko.core.di.annotation.scope.FragmentScope
import by.vzhilko.core.ui.viewmodel.ViewModelFactory
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import by.vzhilko.list.di.module.ImageListModule
import by.vzhilko.list.presentation.dialog.ImageListDialogFragment
import by.vzhilko.list.presentation.fragment.ImageListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ImageListModule::class])
interface ImageListComponent : ViewModelFactoryProvider {

    fun inject(fragment: ImageListFragment)

    fun inject(fragment: ImageListDialogFragment)

    override fun getViewModelFactory(): ViewModelFactory

    interface Provider {
        fun getImageListComponent(): ImageListComponent
    }

}