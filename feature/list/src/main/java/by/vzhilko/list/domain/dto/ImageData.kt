package by.vzhilko.list.domain.dto

data class ImageData(
    val id: Int,
    val tags: String,
    val previewUrl: String,
    val largeImageUrl: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String
)
