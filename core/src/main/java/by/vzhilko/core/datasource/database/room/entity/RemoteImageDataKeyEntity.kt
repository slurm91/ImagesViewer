package by.vzhilko.core.datasource.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val REMOTE_IMAGE_DATA_KEY_TABLE_NAME: String = "remote_image_data_key_table"
const val REMOTE_IMAGE_DATA_KEY_TABLE_ID_FIELD_NAME: String = "id"
const val REMOTE_IMAGE_DATA_KEY_TABLE_PREVIOUS_PAGE_FIELD_NAME: String = "previous_page"
const val REMOTE_IMAGE_DATA_KEY_TABLE_NEXT_PAGE_FIELD_NAME: String = "next_page"

@Entity(tableName = REMOTE_IMAGE_DATA_KEY_TABLE_NAME)
data class RemoteImageDataKeyEntity(
    @PrimaryKey @ColumnInfo(name = REMOTE_IMAGE_DATA_KEY_TABLE_ID_FIELD_NAME) val id: Int,
    @ColumnInfo(name = REMOTE_IMAGE_DATA_KEY_TABLE_PREVIOUS_PAGE_FIELD_NAME) val previousPage: Int?,
    @ColumnInfo(name = REMOTE_IMAGE_DATA_KEY_TABLE_NEXT_PAGE_FIELD_NAME) val nextPage: Int?
)