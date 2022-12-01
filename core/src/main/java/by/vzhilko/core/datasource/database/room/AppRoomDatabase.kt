package by.vzhilko.core.datasource.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import by.vzhilko.core.datasource.database.room.dao.ImageDataDao
import by.vzhilko.core.datasource.database.room.dao.RemoteImageDataKeyDao
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.datasource.database.room.entity.RemoteImageDataKeyEntity

@Database(entities = [ImageDataEntity::class, RemoteImageDataKeyEntity::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun imageDataDao(): ImageDataDao
    abstract fun remoteImageDataKeyDao(): RemoteImageDataKeyDao
}