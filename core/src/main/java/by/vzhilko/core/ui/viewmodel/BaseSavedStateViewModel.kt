package by.vzhilko.core.ui.viewmodel

import androidx.lifecycle.SavedStateHandle

abstract class BaseSavedStateViewModel(
    protected val savedStateHandle: SavedStateHandle? = null
) : BaseViewModel()