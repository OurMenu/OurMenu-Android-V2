package com.kuit.ourmenu.ui.dummy.di

import com.kuit.ourmenu.ui.dummy.repository.DummyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDummyRepository(dummyRepository: DummyRepository): DummyRepository
}