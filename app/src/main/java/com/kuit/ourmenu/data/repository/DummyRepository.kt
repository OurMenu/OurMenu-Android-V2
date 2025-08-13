package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.DummyService
import com.kuit.ourmenu.utils.auth.TokenManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DummyRepository @Inject constructor(
    private val dummyService: DummyService,
    private val tokenManager: TokenManager
) {
    suspend fun getDummyData() =
        runCatching { dummyService.getDummyData().handleBaseResponse().getOrThrow() }
}