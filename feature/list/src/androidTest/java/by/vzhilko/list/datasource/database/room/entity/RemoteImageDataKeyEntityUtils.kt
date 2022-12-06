package by.vzhilko.list.datasource.database.room.entity

import by.vzhilko.core.datasource.database.room.entity.RemoteImageDataKeyEntity
import by.vzhilko.core.util.list.isEqual

fun getRemoteImageDataKeyFruitList(): List<RemoteImageDataKeyEntity> {
    return listOf(
        RemoteImageDataKeyEntity(
            imageDataId = 634572,
            previousPage = null,
            nextPage = 1
        ),
        RemoteImageDataKeyEntity(
            imageDataId = 2277,
            previousPage = null,
            nextPage = 1
        ),
        RemoteImageDataKeyEntity(
            imageDataId = 1761613,
            previousPage = null,
            nextPage = 1
        ),
        RemoteImageDataKeyEntity(
            imageDataId = 2367029,
            previousPage = null,
            nextPage = 1
        ),
        RemoteImageDataKeyEntity(
            imageDataId = 2918569,
            previousPage = null,
            nextPage = 1
        )
    )
}

fun areRemoteImageDataKeyListsEqual(
    expected: List<RemoteImageDataKeyEntity>,
    actual: List<RemoteImageDataKeyEntity>
): Boolean {
    return expected.isEqual(
        list = actual,
        equalBlock = { first, second ->
            first.imageDataId == second.imageDataId
                    && first.previousPage == second.previousPage
                    && first.nextPage == second.nextPage
        }
    )
}