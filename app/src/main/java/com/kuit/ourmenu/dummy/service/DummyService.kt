package com.kuit.ourmenu.dummy.service

import com.kuit.ourmenu.dummy.data.DummyResponse
import retrofit2.http.GET

interface DummyService {
    @GET("")
    suspend fun getDummyData(): DummyResponse
}