package by.vzhilko.core.di.annotation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class SavedStateViewModelKey(val value: KClass<out ViewModel>)
