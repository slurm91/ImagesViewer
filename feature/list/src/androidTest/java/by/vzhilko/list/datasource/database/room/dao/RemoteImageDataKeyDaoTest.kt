package by.vzhilko.list.datasource.database.room.dao

import androidx.room.withTransaction
import androidx.test.ext.junit.runners.AndroidJUnit4
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.RemoteImageDataKeyEntity
import by.vzhilko.list.datasource.database.room.AppRoomDatabaseTestRule
import by.vzhilko.list.datasource.database.room.entity.areRemoteImageDataKeyListsEqual
import by.vzhilko.list.datasource.database.room.entity.getRemoteImageDataKeyFruitList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class RemoteImageDataKeyDaoTest {

    @get:Rule
    val appRoomDatabaseTestRule: AppRoomDatabaseTestRule = AppRoomDatabaseTestRule()

    @Test
    fun insertAll_whenRemoteImageDataKeyListIsInserted_ListShouldBeSavedInDatabase() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase

        val expected: List<RemoteImageDataKeyEntity> = getRemoteImageDataKeyFruitList()
        val actual: MutableList<RemoteImageDataKeyEntity> = mutableListOf()

        database.apply {
            withTransaction {
                remoteImageDataKeyDao().insertAll(expected)
                expected.forEach {
                    val dataFromDb: RemoteImageDataKeyEntity = remoteImageDataKeyDao().getRemoteKeyById(it.imageDataId)
                    actual.add(dataFromDb)
                }
            }
        }

        val areExpectedAndActualEqual: Boolean = areRemoteImageDataKeyListsEqual(expected, actual)
        Assert.assertTrue(areExpectedAndActualEqual)
    }

    @Test
    fun insertAll_whenRemoteImageDataKeyListIsInsertedWithExistedImageDataIds_ListShouldBeUpdatedInDatabase() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originKeyList: List<RemoteImageDataKeyEntity> = getRemoteImageDataKeyFruitList()

        val expected: List<RemoteImageDataKeyEntity> = originKeyList.map { it.copy(previousPage = 1, nextPage = 3) }
        val actual: MutableList<RemoteImageDataKeyEntity> = mutableListOf()

        database.apply {
            withTransaction {
                remoteImageDataKeyDao().insertAll(originKeyList)
                remoteImageDataKeyDao().insertAll(expected)
                expected.forEach {
                    val dataFromDb: RemoteImageDataKeyEntity = remoteImageDataKeyDao().getRemoteKeyById(it.imageDataId)
                    actual.add(dataFromDb)
                }
            }
        }

        val areExpectedAndActualEqual: Boolean = areRemoteImageDataKeyListsEqual(expected, actual)
        Assert.assertTrue(areExpectedAndActualEqual)
    }

    @Test
    fun getImageDataCount_whenRemoteImageDataKeyListIsInserted_shouldBeReturnedTheSameRowsCountAsInserted() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originKeyList: List<RemoteImageDataKeyEntity> = getRemoteImageDataKeyFruitList()

        val expected: Int = originKeyList.size
        var actual: Int = 0

        database.apply {
            withTransaction {
                remoteImageDataKeyDao().insertAll(originKeyList)
                actual = remoteImageDataKeyDao().getKeysCount()
            }
        }

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deleteAll_whenAllRowsAreDeleted_shouldBeReturnRowsCountAsZero() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originKeyList: List<RemoteImageDataKeyEntity> = getRemoteImageDataKeyFruitList()

        val expected: Int = 0
        var actual: Int = 0

        database.apply {
            withTransaction {
                remoteImageDataKeyDao().insertAll(originKeyList)
                remoteImageDataKeyDao().deleteAll()
                actual = remoteImageDataKeyDao().getKeysCount()
            }
        }

        Assert.assertEquals(expected, actual)
    }

}