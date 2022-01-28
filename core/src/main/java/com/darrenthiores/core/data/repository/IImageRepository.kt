package com.darrenthiores.core.data.repository

import com.darrenthiores.core.model.domain.ImageDomain
import kotlinx.coroutines.flow.Flow

interface IImageRepository {

    suspend fun getImages():Flow<List<ImageDomain>>

}