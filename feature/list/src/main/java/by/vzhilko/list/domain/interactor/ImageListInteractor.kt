package by.vzhilko.list.domain.interactor

import androidx.paging.PagingData
import by.vzhilko.core.di.annotation.coroutines.IO
import by.vzhilko.core.util.State
import by.vzhilko.list.domain.datasource.IImageListPagingSource
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
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

    fun getImagesPagingDataSource(): IImageListPagingSource {
        return repository.getImagesPagingDataSource()
    }

    fun subscribeOnAndGetImageListPage(query: String? = null): Flow<PagingData<ImageData>> {
        return repository.subscribeOnAndGetImageListPage(query)
    }

}