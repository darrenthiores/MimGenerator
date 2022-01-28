package com.darrenthiores.mimgenerator.di

import com.darrenthiores.core.domain.ImageInteractor
import com.darrenthiores.core.domain.ImageUseCase
import com.darrenthiores.mimgenerator.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ImageUseCase> { ImageInteractor(get()) }
}

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

}