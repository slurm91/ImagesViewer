package by.vzhilko.list.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.list.domain.datasource.IImageListPagingSource
import by.vzhilko.core.dto.ImageData

class ImageListPagingSource(
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageData>>
) : PagingSource<Int, ImageData>(), IImageListPagingSource {

    private var query: String? = null

    override fun getRefreshKey(state: PagingState<Int, ImageData>): Int? {
        Log.d("myTag", "getRefreshKey start page:")
        return state.anchorPosition?.let { anchorPosition: Int ->
            val anchorPage: LoadResult.Page<Int, ImageData>? = state.closestPageToPosition(anchorPosition)
            val page: Int? = anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            Log.d("myTag", "getRefreshKey page: ${page}")
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
            Log.d("myTag", "load after request thread: ${Thread.currentThread().name}" +
                    " params.loadSize: ${params.loadSize}"
            )

            when(response) {
                is NetworkState.Error -> {
                    Log.d("myTag", "load NetworkState.Error error: ${response.error}")
                    LoadResult.Error(response.error)
                }
                is NetworkState.Success -> {
                    Log.d("myTag", "load NetworkState.Success images size: ${response.response.hits.size}")
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

    override fun changeQuery(query: String?) {
        this.query = query
    }

}