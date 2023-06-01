package by.vzhilko.core.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import by.vzhilko.core.dto.ImageData

class ArgumentsViewModel(
    savedStateHandle: SavedStateHandle? = null
) : BaseSavedStateViewModel(savedStateHandle) {

    var imageDetailsScreenArgs: ImageData? = null

}