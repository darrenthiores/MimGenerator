package com.darrenthiores.mimgenerator

import android.app.Application
import com.darrenthiores.core.di.networkModule
import com.darrenthiores.core.di.repositoryModule
import com.darrenthiores.mimgenerator.di.useCaseModule
import com.darrenthiores.mimgenerator.di.viewModelModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){

            Timber.plant(Timber.DebugTree())

        }

        startKoin {

            androidLogger(Level.NONE)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )

        }

    }
}