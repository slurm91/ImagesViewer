package by.vzhilko.list.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import by.vzhilko.core.ui.viewmodel.BaseViewModel
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.interactor.ImageListInteractor
import by.vzhilko.list.presentation.model.ImageDataListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val interactor: ImageListInteractor
) : BaseViewModel() {

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("fruits")
    val queryStateFlow: StateFlow<String> = _queryStateFlow.asStateFlow()

    private val _imageListStateStateFlow: MutableStateFlow<ImageDataListState> =
        MutableStateFlow(ImageDataListState.NO_STATE)
    val imageListStateStateFlow: StateFlow<ImageDataListState> =
        _imageListStateStateFlow.asStateFlow()

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
        if (query != _queryStateFlow.value) {
            _queryStateFlow.value = query ?: ""
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