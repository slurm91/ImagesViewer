package by.vzhilko.list.di.module

import by.vzhilko.core.di.annotation.viewmodel.ViewModelKey
import by.vzhilko.core.ui.viewmodel.AssistedSavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.BaseSavedStateViewModel
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AbstractImageListModule {

    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    @Binds
    abstract fun bindImageListViewModelFactory(viewModel: ImageListViewModel.Factory): AssistedSavedStateViewModelFactory<out BaseSavedStateViewModel>

}