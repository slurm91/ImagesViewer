package by.vzhilko.details.viewmodel

import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.BaseViewModel
import by.vzhilko.core.util.DefaultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImageDetailsViewModel : BaseViewModel() {

    private val _imageDataStateFlow: MutableStateFlow<DefaultState<ImageData>> = MutableStateFlow(DefaultState.NoState)
    val imageDataStateFlow: StateFlow<DefaultState<ImageData>> = _imageDataStateFlow.asStateFlow()

    fun updateImageData(data: ImageData) {
        _imageDataStateFlow.value = DefaultState.Success(data)
    }

}