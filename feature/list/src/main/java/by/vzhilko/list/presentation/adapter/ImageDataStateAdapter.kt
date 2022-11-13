package by.vzhilko.list.presentation.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import by.vzhilko.list.presentation.adapter.holder.ImageDataStateViewHolder
import by.vzhilko.list.presentation.view.ImageDataListStateView

class ImageDataStateAdapter(
    private val onRetryAction: (() -> Unit)? = null
) : LoadStateAdapter<ImageDataStateViewHolder>() {

    override fun onBindViewHolder(holder: ImageDataStateViewHolder, loadState: LoadState) {
        holder.populate(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ImageDataStateViewHolder {
        val view = ImageDataListStateView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        return ImageDataStateViewHolder(view, onRetryAction)
    }

}