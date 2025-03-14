package com.kuit.ourmenu.data.di

import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.data.repository.DummyRepository
import com.kuit.ourmenu.data.service.AuthService
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
        // 각 Repository 에서 사용할 Service, TokenManager 를 주입
        dummyService: DummyService,
        tokenManager: TokenManager
    ): DummyRepository {
        return DummyRepository(dummyService, tokenManager)
    }

    @Provides
    @Singleton
    fun providesAuthRepository(
        authService: AuthService,
        tokenManager: TokenManager
    ): AuthRepository{
        return AuthRepository(authService, tokenManager)
    }
}