package by.vzhilko.list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.datasource.ImageListPagingSource
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageListRepository @Inject constructor(
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageData>>
) : IImageListRepository {

    override fun subscribeOnAndGetImageListPage(query: String?): Flow<PagingData<ImageData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                maxSize = 100,
                enablePlaceholders = false,
            )
        ) {
            ImageListPagingSource(
                apiService = apiService,
                mapper = mapper,
                query = query
            )
        }.flow
    }

}