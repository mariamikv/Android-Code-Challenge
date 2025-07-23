package com.mariam.android.challenge.core.data.di

import com.mariam.android.challenge.core.data.NetworkConstants
import com.mariam.android.challenge.core.data.network.service.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(
        moshiBuilder: Moshi.Builder,
    ): Moshi = moshiBuilder.build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: Retrofit.Builder,
    ): Retrofit = retrofitBuilder
        .baseUrl(NetworkConstants.BASE_URL)
        .build()
}
