package com.darrenthiores.core.di

import android.os.Build
import com.darrenthiores.core.data.remote.ApiService
import com.darrenthiores.core.BuildConfig
import com.darrenthiores.core.data.remote.RemoteDataSource
import com.darrenthiores.core.data.repository.IImageRepository
import com.darrenthiores.core.data.repository.ImageRepository
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {

            val hostName = BuildConfig.BASE_URL
            val certificatePinner = CertificatePinner.Builder()
                .add(hostName, "sha256/PIPlMcEvYxPiCjqlMRbPJj7W+yOJQWVn4z3ZLbLp/TA=")
                .add(hostName, "sha256/l5bIpOoAf+ziMnsM8s8qazz9qPqAGsiScS4CbFBHsV8=")
                .build()

            okHttpClient.certificatePinner(certificatePinner)

        }

        okHttpClient.build()

    }

    single {
        Retrofit.Builder()
            .baseUrl("https://${BuildConfig.BASE_URL}/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

}

val repositoryModule = module {

    single { RemoteDataSource(get()) }
    single<IImageRepository> { ImageRepository(get()) }

}