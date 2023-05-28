package by.vzhilko.core.datasource.database.config

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultDatabaseConfig @Inject constructor() : IDatabaseConfig {

    override fun getDatabaseName(): String {
        return "image_viewer_database"
    }

}