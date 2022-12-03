package by.vzhilko.list.di.component

import by.vzhilko.core.di.annotation.scope.FragmentScope
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactoryProvider
import by.vzhilko.core.ui.viewmodel.ViewModelFactory
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import by.vzhilko.list.di.module.ImageListModule
import by.vzhilko.list.presentation.dialog.ImageListDialogFragment
import by.vzhilko.list.presentation.fragment.ImageListFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ImageListModule::class])
interface ImageListComponent : SavedStateViewModelFactoryProvider {

    fun inject(fragment: ImageListFragment)

    fun inject(fragment: ImageListDialogFragment)

    override fun getSavedStateViewModelFactory(): SavedStateViewModelFactory

    interface Provider {
        fun getImageListComponent(): ImageListComponent
    }

}