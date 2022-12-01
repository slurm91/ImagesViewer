package by.vzhilko.core.di.module.database

import by.vzhilko.core.datasource.database.config.DefaultDatabaseConfig
import by.vzhilko.core.datasource.database.config.IDatabaseConfig
import dagger.Binds
import dagger.Module

@Module(includes = [RoomDatabaseModule::class])
interface DatabaseModule {

    @Binds
    fun bindDefaultDatabaseConfig(config: DefaultDatabaseConfig): IDatabaseConfig

}