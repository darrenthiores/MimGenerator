package com.darrenthiores.core.data.local

import com.darrenthiores.core.model.data.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val imageDao: ImageDao) {

    suspend fun insetImage(image: ImageEntity) {
        imageDao.insertImage(image)
    }

    fun getImages():Flow<List<ImageEntity>> = imageDao.getImages()

}