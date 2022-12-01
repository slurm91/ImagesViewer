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

    @Query("SELECT * FROM $IMAGE_DATA_TABLE_NAME" +
            " INNER JOIN $REMOTE_IMAGE_DATA_KEY_TABLE_NAME" +
            " ON $IMAGE_DATA_TABLE_NAME.id == $REMOTE_IMAGE_DATA_KEY_TABLE_NAME.id" +
            " ORDER BY $REMOTE_IMAGE_DATA_KEY_TABLE_NAME.next_page")
    fun getPagingSource(): PagingSource<Int, ImageDataEntity>

    @Query("SELECT * FROM $IMAGE_DATA_TABLE_NAME WHERE $IMAGE_DATA_TABLE_ID_FIELD_NAME = :id")
    suspend fun getImageDataById(id: Int): ImageDataEntity

    @Query("SELECT COUNT(*) FROM $IMAGE_DATA_TABLE_NAME")
    suspend fun getImageDataCount(): Int

    @Query("DELETE FROM $IMAGE_DATA_TABLE_NAME")
    suspend fun deleteAll()

}