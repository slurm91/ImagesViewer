package by.vzhilko.list.data.dto

import com.google.gson.annotations.SerializedName

data class ImagesContainerDto(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageDto>
)