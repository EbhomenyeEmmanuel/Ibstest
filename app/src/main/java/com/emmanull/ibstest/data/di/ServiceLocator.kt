package com.emmanull.ibstest.data.di

import android.content.Context
import com.emmanull.ibstest.data.HomeRepositoryImpl
import com.emmanull.ibstest.data.api.ApiService
import com.emmanull.ibstest.data.api.ConnectionManager
import com.emmanull.ibstest.data.api.interceptors.LoggingInterceptor
import com.emmanull.ibstest.data.api.interceptors.NetworkStatusInterceptor
import com.emmanull.ibstest.domain.repositories.HomeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceLocator(context: Context) {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val connectionManager: ConnectionManager by lazy {
        ConnectionManager(context)
    }

    private val networkStatusInterceptor: NetworkStatusInterceptor by lazy {
        NetworkStatusInterceptor(connectionManager)
    }

    private val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        provideHttpLoggingInterceptor(
            LoggingInterceptor()
        )
    }

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient
            .Builder()
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private val homeRepository: HomeRepository? = null

    fun provideHomeRepository(): HomeRepository {
        synchronized(this) {
            return homeRepository ?: createHomeRepository()
        }
    }

    private fun createHomeRepository(): HomeRepositoryImpl {
        return HomeRepositoryImpl(apiService)
    }

    private fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}