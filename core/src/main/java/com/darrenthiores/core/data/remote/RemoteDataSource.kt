package com.darrenthiores.core.data.remote

import com.darrenthiores.core.model.data.response.Memes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getImages():Flow<List<Memes>> = flow {

        val response = apiService.getImages().data.memes

        if(response.isNotEmpty()) emit(response)

    }.flowOn(Dispatchers.IO)

}