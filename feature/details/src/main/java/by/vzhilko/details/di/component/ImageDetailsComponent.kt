package by.vzhilko.details.di.component

import by.vzhilko.core.di.annotation.scope.FragmentScope
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.SavedStateViewModelFactoryProvider
import by.vzhilko.core.ui.viewmodel.ViewModelFactory
import by.vzhilko.core.ui.viewmodel.ViewModelFactoryProvider
import by.vzhilko.details.di.module.ImageDetailsModule
import by.vzhilko.details.fragment.ImageDetailsFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [ImageDetailsModule::class])
interface ImageDetailsComponent : SavedStateViewModelFactoryProvider {

    fun inject(fragment: ImageDetailsFragment)

    override fun getSavedStateViewModelFactory(): SavedStateViewModelFactory

    interface Provider {
        fun getImageDetailsComponent(): ImageDetailsComponent
    }

}