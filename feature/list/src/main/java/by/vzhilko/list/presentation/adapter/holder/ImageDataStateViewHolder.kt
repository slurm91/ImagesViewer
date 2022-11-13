package by.vzhilko.list.presentation.adapter.holder

import androidx.paging.LoadState
import by.vzhilko.core.ui.adapter.holder.BaseViewHolder
import by.vzhilko.list.presentation.model.ImageDataListState
import by.vzhilko.list.presentation.view.ImageDataListStateView

class ImageDataStateViewHolder(
    view: ImageDataListStateView,
    onRetryAction: (() -> Unit)? = null
) : BaseViewHolder<ImageDataListStateView, LoadState>(view) {

    init {
        getView().onRetryAction = onRetryAction
    }

    override fun populate(data: LoadState) {
        when(data) {
            is LoadState.NotLoading -> getView().state = ImageDataListState.NO_STATE
            is LoadState.Loading -> getView().state = ImageDataListState.LOADING
            is LoadState.Error -> {
                val state = ImageDataListState.ERROR.apply { message = data.error.message }
                getView().state = state
            }
        }
    }

}