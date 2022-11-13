package by.vzhilko.list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.util.DefaultState
import by.vzhilko.core.util.State
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.datasource.ImageListPagingSource
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.list.domain.datasource.IImageListPagingSource
import by.vzhilko.core.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageListRepository @Inject constructor(
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageData>>,
    private val pagingSource: IImageListPagingSource
) : IImageListRepository {

    override suspend fun getImages(query: String?): State<List<ImageData>> {
        val state: NetworkState<ImagesContainerDto> = apiService.getImages(
            query = query,
            page = 1,
            pageSize = 30
        )
        return when (state) {
            is NetworkState.Error -> DefaultState.Error(state.error)
            is NetworkState.Success -> return DefaultState.Success(mapper.map(state.response.hits))
        }
    }

    override fun getImagesPagingDataSource(): IImageListPagingSource {
        return pagingSource
    }

    override fun subscribeOnAndGetImageListPage(query: String?): Flow<PagingData<ImageData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                maxSize = 100,
                enablePlaceholders = false,
            )
        ) {
            (pagingSource as ImageListPagingSource).apply { changeQuery(query) }
        }.flow
    }

}