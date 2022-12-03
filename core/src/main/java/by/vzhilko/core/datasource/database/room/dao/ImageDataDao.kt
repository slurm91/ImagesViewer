package by.vzhilko.core.datasource.database.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.vzhilko.core.datasource.database.room.entity.*

@Dao
interface ImageDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ImageDataEntity>)

    @Query("SELECT * FROM $IMAGE_DATA_TABLE_NAME")
    fun getPagingSource(): PagingSource<Int, ImageDataEntity>

    @Query("SELECT * FROM $IMAGE_DATA_TABLE_NAME WHERE $IMAGE_DATA_TABLE_IMAGE_DATA_ID_FIELD_NAME = :id")
    suspend fun getImageDataById(id: Int): ImageDataEntity

    @Query("SELECT COUNT(*) FROM $IMAGE_DATA_TABLE_NAME")
    suspend fun getImageDataCount(): Int

    @Query("DELETE FROM $IMAGE_DATA_TABLE_NAME")
    suspend fun deleteAll()

}