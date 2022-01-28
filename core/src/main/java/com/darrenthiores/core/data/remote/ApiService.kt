package com.darrenthiores.core.data.remote

import com.darrenthiores.core.model.data.response.ImageResponse
import retrofit2.http.GET

interface ApiService {

    @GET("get_memes")
    suspend fun getImages():ImageResponse

}