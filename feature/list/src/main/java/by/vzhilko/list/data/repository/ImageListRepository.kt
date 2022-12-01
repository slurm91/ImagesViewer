package by.vzhilko.list.data.repository

import androidx.paging.*
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.di.annotation.mapper.Mapper
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.list.data.datasource.ImageDataRemoteMediator
import by.vzhilko.list.data.mapper.ImageDataEntityListMapper
import by.vzhilko.list.data.mapper.ImageDataMapper
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val IMAGE_LIST_INITIAL_LOAD_PAGE_SIZE: Int = 100
private const val IMAGE_LIST_PAGE_SIZE: Int = 100
private const val IMAGE_LIST_PREFETCH_DISTANCE_SIZE: Int = 80
private const val IMAGE_LIST_MAX_SIZE: Int = IMAGE_LIST_PAGE_SIZE + 2 * IMAGE_LIST_PREFETCH_DISTANCE_SIZE

class ImageListRepository @Inject constructor(
    private val apiService: ImageListApiService,
    private val database: AppRoomDatabase,
    @Mapper(ImageDataEntityListMapper::class) private val imageDataEntityListMapper: IMapper<List<ImageDto>, List<ImageDataEntity>>,
    @Mapper(ImageDataMapper::class) private val imageDataMapper: IMapper<ImageDataEntity, ImageData>,
    private val connectivityManager: IConnectivityManager
) : IImageListRepository {

    override fun subscribeOnAndGetImageListPage(query: String?): Flow<PagingData<ImageData>> {
        return subscribeOnAndGetImageEntityListPage(query).map { pagingData ->
            pagingData.map {
                imageDataMapper.map(it)
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun subscribeOnAndGetImageEntityListPage(query: String?): Flow<PagingData<ImageDataEntity>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = IMAGE_LIST_INITIAL_LOAD_PAGE_SIZE,
                pageSize = IMAGE_LIST_PAGE_SIZE,
                prefetchDistance = IMAGE_LIST_PREFETCH_DISTANCE_SIZE,
                maxSize = IMAGE_LIST_MAX_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { database.imageDataDao().getPagingSource() },
            remoteMediator = ImageDataRemoteMediator(
                database = database,
                apiService = apiService,
                mapper = imageDataEntityListMapper,
                connectivityManager = connectivityManager,
                query = query
            )
        ).flow
    }

}