package com.darrenthiores.core.data.local

import androidx.room.Database
import com.darrenthiores.core.model.data.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class ImageDb {

    abstract fun imageDao():ImageDao

}