package by.vzhilko.list.presentation.view

import androidx.databinding.BindingAdapter
import by.vzhilko.list.presentation.model.ImageDataListState

@BindingAdapter("app:imageDataStateViewState")
fun setImageDataStateViewState(view: ImageDataListStateView, state: ImageDataListState) {
    if (view.state != state) {
        view.state = state
    }
}

@BindingAdapter("onImageDataViewRetryAction")
fun setOnImageDataViewRetryAction(view: ImageDataListStateView, onRetryAction: () -> Unit) {
    view.onRetryAction = onRetryAction
}