package by.vzhilko.core.datasource.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

const val IMAGE_DATA_TABLE_NAME: String = "image_data_table"
const val IMAGE_DATA_TABLE_ID_FIELD_NAME: String = "id"
const val IMAGE_DATA_TABLE_IMAGE_DATA_ID_FIELD_NAME: String = "image_data_id"
const val IMAGE_DATA_TABLE_TAGS_FIELD_NAME: String = "tags"
const val IMAGE_DATA_TABLE_PREVIEW_URL_FIELD_NAME: String = "preview_url"
const val IMAGE_DATA_TABLE_LARGE_IMAGE_URL_FIELD_NAME: String = "large_image_url"
const val IMAGE_DATA_TABLE_DOWNLOADS_FIELD_NAME: String = "downloads"
const val IMAGE_DATA_TABLE_LIKES_FIELD_NAME: String = "likes"
const val IMAGE_DATA_TABLE_COMMENTS_FIELD_NAME: String = "comments"
const val IMAGE_DATA_TABLE_USER_FIELD_NAME: String = "user"

@Entity(
    tableName = IMAGE_DATA_TABLE_NAME,
    indices = [Index(value = [IMAGE_DATA_TABLE_IMAGE_DATA_ID_FIELD_NAME], unique = true)]
)
data class ImageDataEntity(
    @ColumnInfo(name = IMAGE_DATA_TABLE_ID_FIELD_NAME) @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = IMAGE_DATA_TABLE_IMAGE_DATA_ID_FIELD_NAME) val imageDataId: Int,
    @ColumnInfo(name = IMAGE_DATA_TABLE_TAGS_FIELD_NAME) val tags: String,
    @ColumnInfo(name = IMAGE_DATA_TABLE_PREVIEW_URL_FIELD_NAME) val previewUrl: String,
    @ColumnInfo(name = IMAGE_DATA_TABLE_LARGE_IMAGE_URL_FIELD_NAME) val largeImageUrl: String,
    @ColumnInfo(name = IMAGE_DATA_TABLE_DOWNLOADS_FIELD_NAME) val downloads: Int,
    @ColumnInfo(name = IMAGE_DATA_TABLE_LIKES_FIELD_NAME) val likes: Int,
    @ColumnInfo(name = IMAGE_DATA_TABLE_COMMENTS_FIELD_NAME) val comments: Int,
    @ColumnInfo(name = IMAGE_DATA_TABLE_USER_FIELD_NAME) val user: String,
)