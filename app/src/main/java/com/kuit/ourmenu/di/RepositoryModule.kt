package com.kuit.ourmenu.di

import com.kuit.ourmenu.dummy.repository.DummyRepository
import com.kuit.ourmenu.dummy.service.DummyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideDummyRepository(dummyService: DummyService): DummyRepository {
        return DummyRepository(dummyService)
    }
}
