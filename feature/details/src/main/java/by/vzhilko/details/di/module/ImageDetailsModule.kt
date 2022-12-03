package by.vzhilko.details.di.module

import androidx.lifecycle.ViewModel
import by.vzhilko.core.di.annotation.viewmodel.ViewModelKey
import by.vzhilko.details.viewmodel.ImageDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [AbstractImageDetailsModule::class])
class ImageDetailsModule {

    /*@IntoMap
    @ViewModelKey(ImageDetailsViewModel::class)
    @Provides
    fun provideImageDetailsViewModel(): ViewModel {
        return ImageDetailsViewModel()
    }*/

}