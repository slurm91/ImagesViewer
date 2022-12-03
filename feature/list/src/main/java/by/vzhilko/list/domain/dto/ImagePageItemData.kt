package by.vzhilko.list.domain.dto

import by.vzhilko.core.dto.ImageData

sealed class ImagePageItemData {
    data class Data(val imageData: ImageData) : ImagePageItemData()
    data class Separator(val description: String) : ImagePageItemData()
}