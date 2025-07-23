package com.mariam.android.challenge.core.data.di

import com.mariam.android.challenge.core.data.repository.AppRepositoryImpl
import com.mariam.android.challenge.core.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAppRepository(
        appRepository: AppRepositoryImpl,
    ): AppRepository
}
