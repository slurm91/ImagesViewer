package by.vzhilko.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.BaseSavedStateViewModel
import by.vzhilko.core.util.DefaultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ImageDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseSavedStateViewModel(savedStateHandle) {

    private val _imageDataStateFlow: MutableStateFlow<DefaultState<ImageData>> = MutableStateFlow(DefaultState.NoState)
    val imageDataStateFlow: StateFlow<DefaultState<ImageData>> = _imageDataStateFlow.asStateFlow()

    fun updateImageData(data: ImageData) {
        _imageDataStateFlow.value = DefaultState.Success(data)
    }

}