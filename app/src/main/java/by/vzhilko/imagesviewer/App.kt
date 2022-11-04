package by.vzhilko.imagesviewer

import android.app.Application
import by.vzhilko.core.di.IDIContainerProvider
import by.vzhilko.imagesviewer.di.DI

class App : Application(), IDIContainerProvider {

    override fun onCreate() {
        super.onCreate()
        DI.init(this)
    }

    override fun <T> getComponentProvider(): T {
        return DI.appComponent as T
    }

}