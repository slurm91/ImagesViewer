package by.vzhilko.list.data.api

import by.vzhilko.core.datasource.network.NetworkState
import by.vzhilko.list.data.dto.ImagesContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageListApiService {

    @GET(".")
    suspend fun getImages(
        @Query("q") query: String? = null,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("image_type") imageType: String = "photo",
        @Query("pretty") pretty: Boolean = true
    ): NetworkState<ImagesContainerDto>

}