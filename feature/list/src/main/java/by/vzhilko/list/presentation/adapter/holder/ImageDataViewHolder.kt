package by.vzhilko.list.presentation.adapter.holder

import android.view.View
import by.vzhilko.core.ui.adapter.holder.BaseViewHolder
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.presentation.view.ImageDataView

class ImageDataViewHolder(
    view: View,
    onShowDetailsOfferAction: ((data: ImageData) -> Unit)?
) : BaseViewHolder<ImageDataView, ImageData>(view) {

    init {
        getView().setOnClickListener {
            onShowDetailsOfferAction?.invoke(data)
        }
    }

    override fun populate(data: ImageData) {
        super.populate(data)
        getView().populate(data)
    }

}