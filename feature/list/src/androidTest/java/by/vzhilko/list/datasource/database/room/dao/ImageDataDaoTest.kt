package by.vzhilko.list.datasource.database.room.dao

import androidx.paging.PagingSource
import androidx.room.withTransaction
import androidx.test.ext.junit.runners.AndroidJUnit4
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.list.datasource.database.room.AppRoomDatabaseTestRule
import by.vzhilko.list.datasource.database.room.entity.areImageDataEntityListsEqual
import by.vzhilko.list.datasource.database.room.entity.getImageDataEntityFruitList
import by.vzhilko.list.datasource.paging.getData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ImageDataDaoTest {

    @get:Rule
    val appRoomDatabaseTestRule: AppRoomDatabaseTestRule = AppRoomDatabaseTestRule()

    @Test
    fun insertAll_whenImageDataListIsInserted_ListShouldBeSavedInDatabase() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase

        val expected: List<ImageDataEntity> = getImageDataEntityFruitList()
        val actual: MutableList<ImageDataEntity> = mutableListOf()

        database.apply {
            withTransaction {
                imageDataDao().insertAll(expected)
                expected.forEach {
                    val dataFromDb: ImageDataEntity = imageDataDao().getImageDataById(it.imageDataId)
                    actual.add(dataFromDb)
                }
            }
        }

        val areExpectedAndActualEqual: Boolean = areImageDataEntityListsEqual(expected, actual)
        Assert.assertTrue(areExpectedAndActualEqual)
    }

    @Test
    fun insertAll_whenImageDataFruitIsInsertedWithExistedImageDataIds_ListShouldBeUpdatedInDatabase() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originFruitList: List<ImageDataEntity> = getImageDataEntityFruitList()

        val expected: List<ImageDataEntity> = originFruitList.map { it.copy(likes = 0) }
        val actual: MutableList<ImageDataEntity> = mutableListOf()

        database.apply {
            withTransaction {
                imageDataDao().insertAll(originFruitList)
                imageDataDao().insertAll(expected)
                expected.forEach {
                    val dataFromDb: ImageDataEntity = imageDataDao().getImageDataById(it.imageDataId)
                    actual.add(dataFromDb)
                }
            }
        }

        val areExpectedAndActualEqual: Boolean = areImageDataEntityListsEqual(expected, actual)
        Assert.assertTrue(areExpectedAndActualEqual)
    }

    @Test
    fun getPagingSource_whenImageDataListIsInserted_shouldBeReturnedTheSameData() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        var pagingSource: PagingSource<Int, ImageDataEntity>? = null

        val expected: List<ImageDataEntity> = getImageDataEntityFruitList()
        val actual: List<ImageDataEntity>

        database.apply {
            withTransaction {
                imageDataDao().insertAll(expected)
                pagingSource = imageDataDao().getPagingSource()
            }
        }
        actual = pagingSource?.getData() ?: listOf()
        val areExpectedAndActualEqual: Boolean = areImageDataEntityListsEqual(expected, actual)
        Assert.assertTrue(areExpectedAndActualEqual)
    }

    @Test
    fun getImageDataCount_whenImageDataListIsInserted_shouldBeReturnedTheSameRowsCountAsInserted() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originFruitList: List<ImageDataEntity> = getImageDataEntityFruitList()

        val expected: Int = originFruitList.size
        var actual: Int = 0

        database.apply {
            withTransaction {
                imageDataDao().insertAll(originFruitList)
                actual = imageDataDao().getImageDataCount()
            }
        }

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun deleteAll_whenAllRowsAreDeleted_shouldBeReturnRowsCountAsZero() = runTest {
        val database: AppRoomDatabase = appRoomDatabaseTestRule.testDatabase
        val originFruitList: List<ImageDataEntity> = getImageDataEntityFruitList()

        val expected: Int = 0
        var actual: Int = 0

        database.apply {
            withTransaction {
                imageDataDao().insertAll(originFruitList)
                imageDataDao().deleteAll()
                actual = imageDataDao().getImageDataCount()
            }
        }

        Assert.assertEquals(expected, actual)
    }

}