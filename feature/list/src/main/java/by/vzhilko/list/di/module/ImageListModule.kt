package by.vzhilko.list.di.module

import androidx.lifecycle.ViewModel
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.datasource.network.retrofit.extension.createApi
import by.vzhilko.core.di.annotation.mapper.Mapper
import by.vzhilko.core.di.annotation.viewmodel.ViewModelKey
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.repository.ImageListRepository
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.list.data.mapper.ImageDataEntityListMapper
import by.vzhilko.list.data.mapper.ImageDataMapper
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

    @Mapper(ImageDataEntityListMapper::class)
    @Provides
    fun provideImageDataEntityListMapper(): IMapper<List<ImageDto>, List<ImageDataEntity>> {
        return ImageDataEntityListMapper()
    }

    @Mapper(ImageDataMapper::class)
    @Provides
    fun provideImageDataMapper(): IMapper<ImageDataEntity, ImageData> {
        return ImageDataMapper()
    }

    @Provides
    fun provideImageListRepository(
        apiService: ImageListApiService,
        database: AppRoomDatabase,
        @Mapper(ImageDataEntityListMapper::class) imageDataEntityListMapper: IMapper<List<ImageDto>, List<ImageDataEntity>>,
        @Mapper(ImageDataMapper::class) imageDataMapper: IMapper<ImageDataEntity, ImageData>,
        connectivityManager: IConnectivityManager
    ): IImageListRepository {
        return ImageListRepository(apiService, database, imageDataEntityListMapper, imageDataMapper, connectivityManager)
    }

    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    @Provides
    fun provideImageListViewModel(interactor: ImageListInteractor): ViewModel {
        return ImageListViewModel(interactor)
    }

}