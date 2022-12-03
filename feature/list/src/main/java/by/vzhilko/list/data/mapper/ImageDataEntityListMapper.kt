package by.vzhilko.list.data.mapper

import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.util.mapper.IMapper
import by.vzhilko.list.data.dto.ImageDto
import javax.inject.Inject

class ImageDataEntityListMapper @Inject constructor() : IMapper<List<ImageDto>, List<ImageDataEntity>> {

    override fun map(from: List<ImageDto>): List<ImageDataEntity> {
        return from.map {
            ImageDataEntity(
                imageDataId = it.id,
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