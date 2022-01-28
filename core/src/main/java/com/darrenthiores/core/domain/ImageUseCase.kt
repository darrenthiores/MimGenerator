package com.darrenthiores.core.domain

import com.darrenthiores.core.model.domain.ImageDomain
import kotlinx.coroutines.flow.Flow

interface ImageUseCase {

    suspend fun getImages():Flow<List<ImageDomain>>

}