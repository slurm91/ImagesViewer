package by.vzhilko.list.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.core.dto.ImageData

class ImageListPagingSource(
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageData>>,
    private val query: String?
) : PagingSource<Int, ImageData>() {

    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
        return state.anchorPosition?.let { anchorPosition: Int ->
            val anchorPage: LoadResult.Page<Int, ImageData>? = state.closestPageToPosition(anchorPosition)
            val page: Int? = anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            page
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageData> {
        return try {
            val currentPage: Int = params.key ?: 1
            val nextPage: Int = currentPage + 1
            val prevPage: Int? = if (currentPage > 1) currentPage - 1 else null

            val response: NetworkState<ImagesContainerDto> = apiService.getImages(
                query = query,
                page = currentPage,
                pageSize = params.loadSize
            )

            when(response) {
                is NetworkState.Error -> {
                    LoadResult.Error(response.error)
                }
                is NetworkState.Success -> {
                    LoadResult.Page(
                        data = mapper.map(response.response.hits),
                        prevKey = prevPage,
                        nextKey = nextPage
                    )
                }
            }
        } catch (error: Throwable) {
            LoadResult.Error(error)
        }
    }

}