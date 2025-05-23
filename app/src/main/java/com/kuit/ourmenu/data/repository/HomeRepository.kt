package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.HomeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val homeService: HomeService,
) {
    suspend fun getHome() = runCatching {
        homeService.getHome().handleBaseResponse().getOrThrow()
    }
}