package by.vzhilko.list.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import by.vzhilko.core.ui.viewmodel.BaseViewModel
import by.vzhilko.list.data.datasource.ImageListPagingSource
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

    private var imageListPagingSource: ImageListPagingSource? = null

    private val _queryStateFlow: MutableStateFlow<String> = MutableStateFlow("fruits")
    val queryStateFlow: StateFlow<String> = _queryStateFlow.asStateFlow()

    private val _imageListStateStateFlow: MutableStateFlow<ImageDataListState> =
        MutableStateFlow(ImageDataListState.NO_STATE)
    val imageListStateStateFlow: StateFlow<ImageDataListState> =
        _imageListStateStateFlow.asStateFlow()

    private val _imageListRetryStateFlow: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 0)
    val imageListRetryStateFlow: SharedFlow<Unit> = _imageListRetryStateFlow.asSharedFlow()

    /*val imageDataPagingDataFlow: Flow<PagingData<ImageData>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            maxSize = 100,
            enablePlaceholders = false,
        )
    ) {
        imageListPagingSource = (interactor.getImagesPagingDataSource() as ImageListPagingSource)
        updateQuery(_queryStateFlow.value)
        imageListPagingSource
    }.flow.cachedIn(viewModelScope)*/

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val imageDataPagingDataFlow: Flow<PagingData<ImageData>> =
        queryStateFlow
            .debounce(1000L)
            .distinctUntilChanged { old, new ->
                Log.d("myTag", "ViewModel imageDataPagingDataFlow distinctUntilChanged old: ${old} new: ${new}")
                old == new
            }
            .flatMapLatest { query: String ->
                Log.d("myTag", "ViewModel imageDataPagingDataFlow flatMapLatest query: ${query}")
                interactor.subscribeOnAndGetImageListPage(query)
            }.cachedIn(viewModelScope)

    fun updateQuery(query: String?) {
        if (query != _queryStateFlow.value) {
            Log.d("myTag", "ViewModel updateQuery query: ${query}")
            //imageListPagingSource?.changeQuery(query)
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