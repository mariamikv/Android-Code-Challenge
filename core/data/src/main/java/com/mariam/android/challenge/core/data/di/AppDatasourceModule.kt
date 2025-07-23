package com.mariam.android.challenge.core.data.di

import com.mariam.android.challenge.core.data.network.datasource.AppDatasourceImpl
import com.mariam.android.challenge.core.data.network.datasource.IAppDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDatasourceModule {
    @Binds
    abstract fun bindAppDatasource(
        appDatasource: AppDatasourceImpl,
    ): IAppDatasource
}