package com.kuit.ourmenu.data.di

import com.kuit.ourmenu.data.service.AuthService
import com.kuit.ourmenu.data.service.CacheService
import com.kuit.ourmenu.data.service.DummyService
import com.kuit.ourmenu.data.service.HomeService
import com.kuit.ourmenu.data.service.MapService
import com.kuit.ourmenu.data.service.MenuFolderService
import com.kuit.ourmenu.data.service.UserService
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
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providesMapService(retrofit: Retrofit): MapService =
        retrofit.create(MapService::class.java)

    @Provides
    @Singleton
    fun providesCacheService(retrofit: Retrofit): CacheService =
        retrofit.create(CacheService::class.java)
        
    @Provides
    @Singleton
    fun provideMenuFolderService(retrofit: Retrofit): MenuFolderService =
        retrofit.create(MenuFolderService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

}