package com.kuit.ourmenu.ui.dummy.service

import com.kuit.ourmenu.ui.dummy.data.DummyResponse

interface DummyService {
//    @GET("")
    suspend fun getDummyData(): DummyResponse
}