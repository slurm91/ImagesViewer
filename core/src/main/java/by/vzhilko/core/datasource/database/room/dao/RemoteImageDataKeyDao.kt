package by.vzhilko.core.datasource.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.vzhilko.core.datasource.database.room.entity.REMOTE_IMAGE_DATA_KEY_TABLE_IMAGE_DATA_ID_FIELD_NAME
import by.vzhilko.core.datasource.database.room.entity.REMOTE_IMAGE_DATA_KEY_TABLE_NAME
import by.vzhilko.core.datasource.database.room.entity.RemoteImageDataKeyEntity

@Dao
interface RemoteImageDataKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<RemoteImageDataKeyEntity>)

    @Query("SELECT * FROM $REMOTE_IMAGE_DATA_KEY_TABLE_NAME WHERE $REMOTE_IMAGE_DATA_KEY_TABLE_IMAGE_DATA_ID_FIELD_NAME = :id")
    suspend fun getRemoteKeyById(id: Int): RemoteImageDataKeyEntity

    @Query("DELETE FROM $REMOTE_IMAGE_DATA_KEY_TABLE_NAME")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM $REMOTE_IMAGE_DATA_KEY_TABLE_NAME")
    suspend fun getKeysCount(): Int

}