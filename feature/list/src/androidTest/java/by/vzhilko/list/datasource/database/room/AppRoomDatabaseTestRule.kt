package by.vzhilko.list.datasource.database.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import by.vzhilko.core.datasource.database.room.AppRoomDatabase
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class AppRoomDatabaseTestRule : TestWatcher() {

    lateinit var testDatabase: AppRoomDatabase

    override fun starting(description: Description) {
        testDatabase = createTestDatabase()
    }

    override fun finished(description: Description) {
        testDatabase.close()
    }

    private fun createTestDatabase(): AppRoomDatabase {
        val context: Context = ApplicationProvider.getApplicationContext()
        val clazz: Class<AppRoomDatabase> = AppRoomDatabase::class.java

        return Room.inMemoryDatabaseBuilder(context, clazz).build()
    }

}