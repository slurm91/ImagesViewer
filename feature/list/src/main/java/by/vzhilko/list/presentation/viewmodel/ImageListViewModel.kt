package by.vzhilko.list.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.ui.viewmodel.BaseSavedStateViewModel
import by.vzhilko.list.domain.interactor.ImageListInteractor
import by.vzhilko.list.presentation.model.ImageDataListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val IMAGE_LIST_QUERY_SAVED_STATE_HANDLE_KEY: String = "IMAGE_LIST_QUERY_SAVED_STATE_HANDLE_KEY"
private const val IMAGE_LIST_QUERY_DEFAULT_VALUE: String = "fruits"

@HiltViewModel
class ImageListViewModel @Inject constructor (
    private val interactor: ImageListInteractor,
    savedStateHandle: SavedStateHandle
) : BaseSavedStateViewModel(savedStateHandle) {

    val queryStateFlow: StateFlow<String> = savedStateHandle.getStateFlow(
        IMAGE_LIST_QUERY_SAVED_STATE_HANDLE_KEY,
        IMAGE_LIST_QUERY_DEFAULT_VALUE
    )

    private val _imageListStateStateFlow: MutableStateFlow<ImageDataListState> = MutableStateFlow(ImageDataListState.NO_STATE)
    val imageListStateStateFlow: StateFlow<ImageDataListState> = _imageListStateStateFlow.asStateFlow()

    private val _imageListRetryStateFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val imageListRetryStateFlow: SharedFlow<Unit> = _imageListRetryStateFlow.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val imageDataPagingDataFlow: Flow<PagingData<ImageData>> =
        queryStateFlow
            .debounce(1000L)
            .distinctUntilChanged { old, new -> old == new }
            .flatMapLatest { query: String -> interactor.subscribeOnAndGetImageListPage(query) }
            .cachedIn(viewModelScope)

    fun updateQuery(query: String?) {
        if (query != queryStateFlow.value) {
            savedStateHandle?.set(IMAGE_LIST_QUERY_SAVED_STATE_HANDLE_KEY, query)
        }
    }

    fun retryImageDataLoading() {
        viewModelScope.launch {
            _imageListRetryStateFlow.emit(Unit)
        }
    }

    fun updateImageListState(state: ImageDataListState) {
        _imageListStateStateFlow.value = state
    }

}