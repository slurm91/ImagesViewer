package by.vzhilko.list.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.presentation.adapter.holder.ImageDataViewHolder
import by.vzhilko.list.presentation.view.ImageDataView

class ImageDataAdapter(
    diffCallback: DiffUtil.ItemCallback<ImageData>,
    private val onShowDetailsOfferAction: ((data: ImageData) -> Unit)? = null
) : PagingDataAdapter<ImageData, ImageDataViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ImageDataViewHolder, position: Int) {
        getItem(position)?.let { holder.populate(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageDataViewHolder {
        val view = ImageDataView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        return ImageDataViewHolder(view, onShowDetailsOfferAction)
    }

}