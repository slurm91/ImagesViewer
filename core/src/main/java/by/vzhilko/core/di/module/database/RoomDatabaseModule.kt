package by.vzhilko.core.di.module.database

import android.content.Context
import androidx.room.Room
import by.vzhilko.core.datasource.database.config.IDatabaseConfig
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import by.vzhilko.core.di.annotation.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class RoomDatabaseModule {

    @AppScope
    @Provides
    fun provideRoomDatabase(
        context: Context,
        config: IDatabaseConfig
    ): AppRoomDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java,
            config.getDatabaseName()
        ).build()
    }

}