package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.model.dummy.response.DummyResponse
import com.kuit.ourmenu.data.service.DummyService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DummyRepository @Inject constructor(
    private val dummyService: DummyService
) {
    suspend fun getDummyData() =
        runCatching { dummyService.getDummyData().handleBaseResponse().getOrThrow() }
}