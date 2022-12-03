package by.vzhilko.core.ui.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

class SavedStateViewModelFactory @Inject constructor (
    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out BaseSavedStateViewModel>>
) {

    fun create(owner: SavedStateRegistryOwner, defaultArgs: Bundle? = null): AbstractSavedStateViewModelFactory {
        return object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return viewModelFactories[modelClass]?.create(handle) as T
            }
        }
    }

}