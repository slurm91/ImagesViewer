package by.vzhilko.core.ui.viewmodel

import androidx.lifecycle.SavedStateHandle

interface AssistedSavedStateViewModelFactory<T : BaseSavedStateViewModel> {

    fun create(savedStateHandle: SavedStateHandle): T

}