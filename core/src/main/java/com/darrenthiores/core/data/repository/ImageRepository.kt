package com.darrenthiores.core.data.repository

import com.darrenthiores.core.data.remote.RemoteDataSource
import com.darrenthiores.core.model.domain.ImageDomain
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImageRepository(private val remoteDataSource: RemoteDataSource) : IImageRepository {

    override suspend fun getImages(): Flow<List<ImageDomain>> =
        remoteDataSource.getImages().map { DataMapper.mapResponseToDomain(it) }

}