package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.dummy.request.DummyRequest
import com.kuit.ourmenu.data.model.dummy.response.DummyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DummyService {
    @GET("")
    suspend fun getDummyData(): BaseResponse<DummyResponse>

    @POST("")
    suspend fun postDummyData(
        @Body dummy: DummyRequest
    ): BaseResponse<DummyResponse>
}