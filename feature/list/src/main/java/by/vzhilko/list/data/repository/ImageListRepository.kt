package by.vzhilko.list.data.repository

import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.util.DefaultState
import by.vzhilko.core.util.State
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.list.domain.dto.ImageData
import by.vzhilko.list.domain.repository.IImageListRepository
import javax.inject.Inject

class ImageListRepository @Inject constructor(
    private val apiService: ImageListApiService,
    private val mapper: IMapper<List<ImageDto>, List<ImageData>>
) : IImageListRepository {

    override suspend fun getImages(query: String?): State<List<ImageData>> {
        val state: NetworkState<ImagesContainerDto> = apiService.getImages(query = query)
        return when(state) {
            is NetworkState.Error -> DefaultState.Error(state.error)
            is NetworkState.Success -> return DefaultState.Success(mapper.map(state.response.hits))
        }
    }

}