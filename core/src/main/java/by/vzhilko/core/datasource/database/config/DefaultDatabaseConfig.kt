package by.vzhilko.core.datasource.database.config

import by.vzhilko.core.di.annotation.scope.AppScope
import javax.inject.Inject

@AppScope
class DefaultDatabaseConfig @Inject constructor() : IDatabaseConfig {

    override fun getDatabaseName(): String {
        return "image_viewer_database"
    }

}