package com.kuit.ourmenu.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // TODO : Interceptor , OkHttpClient

    @Provides
    @Singleton
    fun providesRetrofit(
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ourmenu-dev.shop/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

}