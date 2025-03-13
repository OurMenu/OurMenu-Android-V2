package com.kuit.ourmenu.data.di

import com.kuit.ourmenu.data.repository.DummyRepository
import com.kuit.ourmenu.data.service.DummyService
import com.kuit.ourmenu.utils.auth.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDummyRepository(
        dummyService: DummyService,
        tokenManager: TokenManager
    ): DummyRepository {
        return DummyRepository(dummyService, tokenManager)
    }
}