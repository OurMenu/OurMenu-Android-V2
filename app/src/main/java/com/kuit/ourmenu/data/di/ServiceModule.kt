package com.kuit.ourmenu.data.di

import com.kuit.ourmenu.data.service.AccountService
import com.kuit.ourmenu.data.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit): DummyService =
        retrofit.create(DummyService::class.java)

    @Provides
    @Singleton
    fun providesAccountService(retrofit: Retrofit): AccountService =
        retrofit.create(AccountService::class.java)
}