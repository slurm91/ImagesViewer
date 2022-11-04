package by.vzhilko.list.domain.repository

import by.vzhilko.core.util.State
import by.vzhilko.list.domain.dto.ImageData

interface IImageListRepository {

    suspend fun getImages(query: String? = null): State<List<ImageData>>

}