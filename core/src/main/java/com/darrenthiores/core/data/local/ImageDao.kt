package com.darrenthiores.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darrenthiores.core.model.data.entity.ImageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image:ImageEntity)

    @Query("SELECT * FROM imageEntity")
    fun getImages():Flow<List<ImageEntity>>

}