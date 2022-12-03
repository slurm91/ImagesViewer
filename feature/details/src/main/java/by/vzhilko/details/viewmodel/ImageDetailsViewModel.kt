package by.vzhilko.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.AssistedSavedStateViewModelFactory
import by.vzhilko.core.ui.viewmodel.BaseSavedStateViewModel
import by.vzhilko.core.util.DefaultState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImageDetailsViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : BaseSavedStateViewModel(savedStateHandle) {

    private val _imageDataStateFlow: MutableStateFlow<DefaultState<ImageData>> = MutableStateFlow(DefaultState.NoState)
    val imageDataStateFlow: StateFlow<DefaultState<ImageData>> = _imageDataStateFlow.asStateFlow()

    fun updateImageData(data: ImageData) {
        _imageDataStateFlow.value = DefaultState.Success(data)
    }

    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<ImageDetailsViewModel>

}