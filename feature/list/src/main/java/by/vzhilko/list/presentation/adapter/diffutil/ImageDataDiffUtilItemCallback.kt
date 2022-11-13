package by.vzhilko.list.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import by.vzhilko.core.dto.ImageData

class ImageDataDiffUtilItemCallback : DiffUtil.ItemCallback<ImageData>() {

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }

}