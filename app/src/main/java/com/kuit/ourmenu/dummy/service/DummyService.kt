package com.kuit.ourmenu.dummy.service

import com.kuit.ourmenu.dummy.data.DummyResponse

interface DummyService {
//    @GET("")
    suspend fun getDummyData(): DummyResponse
}