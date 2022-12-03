package by.vzhilko.list.data.mapper

import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import by.vzhilko.core.dto.ImageData
import by.vzhilko.core.util.mapper.IMapper
import javax.inject.Inject

class ImageDataMapper @Inject constructor() : IMapper<ImageDataEntity, ImageData> {

    override fun map(from: ImageDataEntity): ImageData {
        return ImageData(
            id = from.imageDataId,
            tags = from.tags,
            previewUrl = from.previewUrl,
            largeImageUrl = from.largeImageUrl,
            downloads = from.downloads,
            likes = from.likes,
            comments = from.comments,
            user = from.user
        )
    }

}