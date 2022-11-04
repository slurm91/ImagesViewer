package by.vzhilko.list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import by.vzhilko.core.ui.viewmodel.BaseViewModel
import by.vzhilko.core.util.DefaultState
import by.vzhilko.core.util.State
import by.vzhilko.list.domain.dto.ImageData
import by.vzhilko.list.domain.interactor.ImageListInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val interactor: ImageListInteractor
) : BaseViewModel() {

    private val _imageListStateFlow: MutableStateFlow<State<List<ImageData>>> = MutableStateFlow(DefaultState.NoState)
    val imageListStateFlow: StateFlow<State<List<ImageData>>> = _imageListStateFlow.asStateFlow()

    fun loadImages(query: String? = null) {
        _imageListStateFlow.value = DefaultState.Loading
        viewModelScope.launch{
            _imageListStateFlow.value = interactor.getImages(query)
        }
    }

}