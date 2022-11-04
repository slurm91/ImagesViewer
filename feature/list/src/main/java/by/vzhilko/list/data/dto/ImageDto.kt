package by.vzhilko.list.data.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id") val id: Int,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewUrl: String,
    @SerializedName("largeImageURL") val largeImageUrl: String,
    @SerializedName("downloads") val downloads: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("comments") val comments: Int,
    @SerializedName("user") val user: String
)