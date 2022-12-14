package by.vzhilko.list.datasource.paging

import androidx.paging.*
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.util.connectivity.IConnectivityManager
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.api.FakeImageApiService
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.datasource.ImageDataRemoteMediator
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.mapper.ImageDataEntityListMapper
import by.vzhilko.list.datasource.database.room.AppRoomDatabaseTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@OptIn(ExperimentalPagingApi::class)
@RunWith(MockitoJUnitRunner::class)
class ImageDataRemoteMediatorTest {

    @get:Rule
    val appRoomDatabaseTestRule: AppRoomDatabaseTestRule = AppRoomDatabaseTestRule()
    private val apiService: ImageListApiService = FakeImageApiService()
    private val mapper: IMapper<List<ImageDto>, List<ImageDataEntity>> = ImageDataEntityListMapper()
    private val connectivityManager: IConnectivityManager = mock()
    private var page: Int = 1
    private var pageSize: Int = 5
    private var query: String? = "fruits"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun load_whenImageListIsFetched_shouldBeReturnedMediatorResultWithEndOfPaginationReachedFalse() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
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
        query = ""
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
        query = null
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
        query = "pie"
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