package by.vzhilko.list.data.mapper

import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.dto.ImageDto
import by.vzhilko.list.domain.dto.ImageData
import javax.inject.Inject

class ImageDataListMapper @Inject constructor() : IMapper<List<ImageDto>, List<ImageData>> {

    override fun map(from: List<ImageDto>): List<ImageData> {
        return from.map {
            ImageData(
                id = it.id,
                tags = it.tags,
                previewUrl = it.previewUrl,
                largeImageUrl = it.largeImageUrl,
                downloads = it.downloads,
                likes = it.likes,
                comments = it.comments,
                user = it.user
            )
        }
    }

}