package by.vzhilko.list.datasource.paging

import androidx.paging.*
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.datasource.network.error.exception.NetworkException
import by.vzhilko.core.datasource.network.error.exception.NoResourceFoundNetworkException
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.datasource.ImageDataRemoteMediator
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.list.data.mapper.ImageDataEntityListMapper
import by.vzhilko.list.datasource.database.room.AppRoomDatabaseTestRule
import by.vzhilko.list.dto.getImageDtoFruitList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalPagingApi::class)
@RunWith(MockitoJUnitRunner::class)
class ImageDataRemoteMediatorTest {

    @get:Rule
    val appRoomDatabaseTestRule: AppRoomDatabaseTestRule = AppRoomDatabaseTestRule()
    private val apiService: ImageListApiService = mock()
    private val mapper: IMapper<List<ImageDto>, List<ImageDataEntity>> = ImageDataEntityListMapper()
    private val connectivityManager: IConnectivityManager = mock()
    private val page: Int = 1
    private val pageSize: Int = 5
    private val query: String = "fruits"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun load_whenImageListIsFetched_shouldBeReturnedMediatorResultWithEndOfPaginationReachedFalse() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        whenever(apiService.getImages(query, page, pageSize)).thenReturn(
            NetworkState.Success(
                ImagesContainerDto(
                    total = 50,
                    totalHits = 5,
                    hits = getImageDtoFruitList()
                )
            )
        )
        val remoteMediator = ImageDataRemoteMediator(
            database = database,
            apiService = apiService,
            mapper = mapper,
            connectivityManager = connectivityManager,
            query = query
        )
        val pagingData = PagingState<Int, ImageDataEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize),
            leadingPlaceholderCount = 0
        )

        val result: RemoteMediator.MediatorResult = remoteMediator.load(LoadType.REFRESH, pagingData)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun load_whenEmptyImageListIsFetched_shouldBeReturnedMediatorResultWithEndOfPaginationReachedTrue() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        whenever(apiService.getImages(query, page, pageSize)).thenReturn(
            NetworkState.Success(
                ImagesContainerDto(
                    total = 50,
                    totalHits = 5,
                    hits = listOf()
                )
            )
        )
        val remoteMediator = ImageDataRemoteMediator(
            database = database,
            apiService = apiService,
            mapper = mapper,
            connectivityManager = connectivityManager,
            query = query
        )
        val pagingData = PagingState<Int, ImageDataEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize),
            leadingPlaceholderCount = 0
        )

        val result: RemoteMediator.MediatorResult = remoteMediator.load(LoadType.REFRESH, pagingData)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun load_whenErrorIsFetched_shouldBeReturnedMediatorResultError() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        whenever(apiService.getImages(query, page, pageSize)).thenReturn(
            NetworkState.Error(NoResourceFoundNetworkException())
        )
        val remoteMediator = ImageDataRemoteMediator(
            database = database,
            apiService = apiService,
            mapper = mapper,
            connectivityManager = connectivityManager,
            query = query
        )
        val pagingData = PagingState<Int, ImageDataEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize),
            leadingPlaceholderCount = 0
        )

        val result: RemoteMediator.MediatorResult = remoteMediator.load(LoadType.REFRESH, pagingData)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun load_whenErrorCodeIs400_shouldBeReturnedMediatorResultWithEndOfPaginationReachedTrue() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        whenever(apiService.getImages(query, page, pageSize)).thenReturn(
            NetworkState.Error(NetworkException(code = 400))
        )
        val remoteMediator = ImageDataRemoteMediator(
            database = database,
            apiService = apiService,
            mapper = mapper,
            connectivityManager = connectivityManager,
            query = query
        )
        val pagingData = PagingState<Int, ImageDataEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize),
            leadingPlaceholderCount = 0
        )

        val result: RemoteMediator.MediatorResult = remoteMediator.load(LoadType.REFRESH, pagingData)
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

}