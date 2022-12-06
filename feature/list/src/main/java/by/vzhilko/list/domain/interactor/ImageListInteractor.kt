package by.vzhilko.list.domain.interactor

import androidx.paging.PagingData
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageListInteractor @Inject constructor (private val repository: IImageListRepository) {

    fun subscribeOnAndGetImageListPage(query: String? = null): Flow<PagingData<ImageData>> {
        return repository.subscribeOnAndGetImageListPage(query)
    }

}