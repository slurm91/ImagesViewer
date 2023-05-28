package by.vzhilko.core.di.module.database

import android.content.Context
import androidx.room.Room
import by.vzhilko.core.datasource.database.config.IDatabaseConfig
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        config: IDatabaseConfig
    ): AppRoomDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java,
            config.getDatabaseName()
        ).build()
    }

}