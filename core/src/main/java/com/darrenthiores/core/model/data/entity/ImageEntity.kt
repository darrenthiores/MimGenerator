package com.darrenthiores.core.model.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imageEntity")
data class ImageEntity(
    @PrimaryKey(autoGenerate = false)
    var image:String
)
