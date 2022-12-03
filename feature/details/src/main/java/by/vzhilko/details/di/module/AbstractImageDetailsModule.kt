package by.vzhilko.details.di.module

import by.vzhilko.core.di.annotation.viewmodel.ViewModelKey
import by.vzhilko.core.ui.viewmodel.AssistedSavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.BaseSavedStateViewModel
import by.vzhilko.details.viewmodel.ImageDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AbstractImageDetailsModule {

    @IntoMap
    @ViewModelKey(ImageDetailsViewModel::class)
    @Binds
    abstract fun bindImageDetailsViewModelFactory(factory: ImageDetailsViewModel.Factory): AssistedSavedStateViewModelFactory<out BaseSavedStateViewModel>

}