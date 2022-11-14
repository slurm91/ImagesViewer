package by.vzhilko.list.domain.repository

import androidx.paging.PagingData
import by.vzhilko.core.dto.ImageData
import kotlinx.coroutines.flow.Flow

interface IImageListRepository {

    fun subscribeOnAndGetImageListPage(query: String? = null): Flow<PagingData<ImageData>>

}