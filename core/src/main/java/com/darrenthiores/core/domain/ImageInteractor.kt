package com.darrenthiores.core.domain

import com.darrenthiores.core.data.repository.ImageRepository
import com.darrenthiores.core.model.domain.ImageDomain
import kotlinx.coroutines.flow.Flow

class ImageInteractor(private val imageRepository: ImageRepository) : ImageUseCase {

    override suspend fun getImages(): Flow<List<ImageDomain>> = imageRepository.getImages()

}