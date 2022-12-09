package by.vzhilko.list.api

import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.core.datasource.network.error.exception.NetworkException
import by.vzhilko.list.data.api.ImageListApiService
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.data.dto.ImagesContainerDto
import by.vzhilko.list.dto.getImageDtoFruitList

class FakeImageApiService : ImageListApiService {

    private val remoteImageDtoList: List<ImageDto> = getImageDtoFruitList()
    private val remoteImageDtoListSize: Int = remoteImageDtoList.size
    private var remoteImageDtoListIndex: Int = 0

    override suspend fun getImages(
        query: String?,
        page: Int,
        pageSize: Int,
        imageType: String,
        pretty: Boolean
    ): NetworkState<ImagesContainerDto> {
        return when(query) {
            "fruits" -> NetworkState.Success(
                ImagesContainerDto(
                    totalHits = 20,
                    total = 10,
                    hits = getImageDtoList(page, pageSize),
                )
            )
            "pie" -> NetworkState.Error(NetworkException(code = 400, "List is empty."))
            null -> NetworkState.Error(NetworkException(code = 500, "Error!"))
            else -> NetworkState.Success(
                ImagesContainerDto(
                    totalHits = 20,
                    total = 10,
                    hits = emptyList(),
                )
            )
        }
    }

    private fun getImageDtoList(page: Int, pageSize: Int): List<ImageDto> {
        return if (page > 0 && pageSize > 0) {
            remoteImageDtoListIndex = page * pageSize
            if (remoteImageDtoListIndex >= remoteImageDtoListSize - 1) {
                remoteImageDtoList
            } else {
                remoteImageDtoList.subList(remoteImageDtoListIndex, remoteImageDtoListSize)
            }
        } else {
            emptyList()
        }
    }

}