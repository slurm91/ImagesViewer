package by.vzhilko.core.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(
    val id: Int,
    val tags: String,
    val previewUrl: String,
    val largeImageUrl: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String
) : Parcelable
