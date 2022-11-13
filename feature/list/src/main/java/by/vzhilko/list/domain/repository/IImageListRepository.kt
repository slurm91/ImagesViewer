package by.vzhilko.list.domain.repository

import androidx.paging.PagingData
import by.vzhilko.core.util.State
import by.vzhilko.list.domain.datasource.IImageListPagingSource
import by.vzhilko.core.dto.ImageData
import kotlinx.coroutines.flow.Flow

interface IImageListRepository {

    suspend fun getImages(query: String? = null): State<List<ImageData>>

    fun getImagesPagingDataSource(): IImageListPagingSource

    fun subscribeOnAndGetImageListPage(query: String? = null): Flow<PagingData<ImageData>>

}