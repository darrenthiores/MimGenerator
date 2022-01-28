package com.darrenthiores.mimgenerator.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.darrenthiores.core.domain.ImageUseCase
import com.darrenthiores.core.model.presenter.Image
import com.darrenthiores.core.utils.DataMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class MainViewModel(private val useCase: ImageUseCase) : ViewModel(){

    fun getImages():LiveData<List<Image>> = runBlocking {
        useCase.getImages().map { DataMapper.mapDomainToPresenter(it) }.asLiveData()
    }

}