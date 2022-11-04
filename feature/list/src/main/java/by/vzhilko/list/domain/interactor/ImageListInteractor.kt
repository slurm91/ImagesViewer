package by.vzhilko.list.domain.interactor

import by.vzhilko.core.di.annotation.coroutines.IO
import by.vzhilko.core.util.State
import by.vzhilko.list.domain.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageListInteractor @Inject constructor (
    @IO private val coroutineDispatcher: CoroutineDispatcher,
    private val repository: IImageListRepository
) {

    suspend fun getImages(query: String? = null): State<List<ImageData>> {
        return withContext(coroutineDispatcher) {
            repository.getImages(query)
        }
    }

}