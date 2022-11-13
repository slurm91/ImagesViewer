package by.vzhilko.list.di.module

import androidx.lifecycle.ViewModel
import by.vzhilko.core.datasource.network.retrofit.extension.createApi
import by.vzhilko.core.di.annotation.viewmodel.ViewModelKey
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.datasource.ImageListPagingSource
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.mapper.ImageDataListMapper
import by.vzhilko.list.data.repository.ImageListRepository
import by.vzhilko.list.domain.datasource.IImageListPagingSource
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.interactor.ImageListInteractor
import by.vzhilko.list.domain.repository.IImageListRepository
import by.vzhilko.list.presentation.viewmodel.ImageListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class ImageListModule {

    @Provides
    fun provideImageListApiService(retrofit: Retrofit): ImageListApiService {
        return retrofit.createApi()
    }

    @Provides
    fun provideImageDataListMapper(): IMapper<List<ImageDto>, List<ImageData>> {
        return ImageDataListMapper()
    }

    @Provides
    fun provideImageListPagingSource(
        apiService: ImageListApiService,
        mapper: IMapper<List<ImageDto>, List<ImageData>>
    ): IImageListPagingSource {
        return ImageListPagingSource(apiService, mapper)
    }

    @Provides
    fun provideImageListRepository(
        apiService: ImageListApiService,
        mapper: IMapper<List<ImageDto>, List<ImageData>>,
        pagingSource: IImageListPagingSource
    ): IImageListRepository {
        return ImageListRepository(apiService, mapper, pagingSource)
    }

    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    @Provides
    fun provideImageListViewModel(interactor: ImageListInteractor): ViewModel {
        return ImageListViewModel(interactor)
    }

}