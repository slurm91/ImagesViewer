package by.vzhilko.imagesviewer.di

import android.content.Context
import by.vzhilko.imagesviewer.di.component.AppComponent
import by.vzhilko.imagesviewer.di.component.DaggerAppComponent

object DI {

    lateinit var appComponent: AppComponent

    fun init(context: Context) {
        appComponent = DaggerAppComponent.builder()
            .context(context)
            .build()
    }

}