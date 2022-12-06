package by.vzhilko.list.datasource.paging

import androidx.paging.PagingSource
import by.vzhilko.core.datasource.database.room.entity.ImageDataEntity
import kotlinx.coroutines.coroutineScope

suspend fun PagingSource<Int, ImageDataEntity>.getData(
    key: Int = 0,
    loadSize: Int = Int.MAX_VALUE,
    placeholdersEnabled: Boolean = false
): List<ImageDataEntity> {
    return coroutineScope {
        val refreshParams = PagingSource.LoadParams.Refresh(
            key = key,
            loadSize = loadSize,
            placeholdersEnabled = placeholdersEnabled
        )

        val result: PagingSource.LoadResult<Int, ImageDataEntity> = load(refreshParams)
        when(result) {
            is PagingSource.LoadResult.Page -> result.data
            else -> listOf()
        }
    }
}