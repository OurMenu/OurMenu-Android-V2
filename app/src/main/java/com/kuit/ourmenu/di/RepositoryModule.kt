package com.kuit.ourmenu.di

import com.kuit.ourmenu.dummy.repository.DummyRepository
import com.kuit.ourmenu.dummy.repositoryimpl.DummyRepositoryImpl
import com.kuit.ourmenu.dummy.service.DummyService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDummyRepository(dummyRepositoryImpl: DummyRepositoryImpl): DummyRepository

}
