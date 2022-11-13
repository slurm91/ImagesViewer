package by.vzhilko.details.view

import androidx.databinding.BindingAdapter
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.util.DefaultState

@BindingAdapter("app:imageDataDetailsViewImageData")
fun setImageDataDetailsViewImageData(view: ImageDataDetailsView, state: DefaultState<ImageData>) {
    if (state is DefaultState.Success) {
        view.populate(state.value)
    }
}