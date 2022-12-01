package by.vzhilko.list.data.datasource

import androidx.paging.*
import androidx.room.withTransaction
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.datasource.database.room.entity.RemoteImageDataKeyEntity
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.datasource.network.error.exception.NetworkException
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto

private const val IMAGE_LIST_START_PAGE_NUMBER: Int = 1

@ExperimentalPagingApi
class ImageDataRemoteMediator(
    private val database: AppRoomDatabase,
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageDataEntity>>,
    private val connectivityManager: IConnectivityManager,
    private val query: String?
) : RemoteMediator<Int, ImageDataEntity>() {

    override suspend fun initialize(): InitializeAction {
        return if (!connectivityManager.isConnected() && database.imageDataDao().getImageDataCount() > 0) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDataEntity>
    ): MediatorResult {
        return try {
            val page: Int = getPageNumber(loadType, state) ?: return MediatorResult.Success(
                endOfPaginationReached = false
            )

            val response: NetworkState<ImagesContainerDto> = apiService.getImages(
                query = query,
                page = page,
                pageSize = getPageSize(state, page)
            )

            return when (response) {
                is NetworkState.Error -> handleNetworkErrorState(response)
                is NetworkState.Success -> handleNetworkSuccessState(loadType, response, page)
            }
        } catch (error: Throwable) {
            MediatorResult.Error(error)
        }
    }

    private suspend fun getPageNumber(
        loadType: LoadType,
        state: PagingState<Int, ImageDataEntity>
    ): Int? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val position: Int = state.anchorPosition?.let { position: Int ->
                    state.closestItemToPosition(position)?.id?.let { imageDataId: Int ->
                        database.remoteImageDataKeyDao()
                            .getRemoteKeyById(imageDataId).nextPage?.minus(1)
                    }
                } ?: IMAGE_LIST_START_PAGE_NUMBER
                position
            }
            LoadType.PREPEND -> {
                val page: Int? = state.pages
                    .firstOrNull { it.data.isNotEmpty() }
                    ?.data?.firstOrNull()
                    ?.let { data ->
                        database.remoteImageDataKeyDao().getRemoteKeyById(data.id)
                    }
                    ?.previousPage
                page
            }
            LoadType.APPEND -> {
                val page = state.pages
                    .lastOrNull { it.data.isNotEmpty() }
                    ?.data?.lastOrNull()
                    ?.let { data ->
                        database.remoteImageDataKeyDao().getRemoteKeyById(data.id)
                    }
                    ?.nextPage
                page
            }
        }
    }

    private fun handleNetworkErrorState(state: NetworkState.Error): MediatorResult {
        return when (state.error) {
            is NetworkException -> {
                val code: Int? = (state.error as? NetworkException)?.code
                return if (code == 400) {
                    MediatorResult.Success(endOfPaginationReached = true)
                } else {
                    MediatorResult.Error(state.error)
                }
            }
            else -> MediatorResult.Error(state.error)
        }
    }

    private suspend fun handleNetworkSuccessState(
        loadType: LoadType,
        state: NetworkState.Success<ImagesContainerDto>,
        page: Int
    ): MediatorResult {
        return if (state.response.hits.isEmpty()) {
            MediatorResult.Success(endOfPaginationReached = true)
        } else {
            database.apply {
                withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        imageDataDao().deleteAll()
                        remoteImageDataKeyDao().deleteAll()
                    }

                    val remoteKeys: List<RemoteImageDataKeyEntity> = state.response.hits.map {
                        RemoteImageDataKeyEntity(
                            id = it.id,
                            previousPage = if (page == IMAGE_LIST_START_PAGE_NUMBER) null else page - 1,
                            nextPage = page + 1
                        )
                    }

                    imageDataDao().insertAll(mapper.map(state.response.hits))
                    remoteImageDataKeyDao().insertAll(remoteKeys)
                }
            }
            MediatorResult.Success(endOfPaginationReached = false)
        }
    }

    private fun getPageSize(state: PagingState<Int, ImageDataEntity>, page: Int): Int {
        return if (page == IMAGE_LIST_START_PAGE_NUMBER) {
            state.config.initialLoadSize
        } else {
            state.config.pageSize
        }
    }

}