package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.auth.response.ReissueTokenResponse
import com.kuit.ourmenu.data.model.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("api/users/reissue-token")
    suspend fun reissueToken(
        @Body refreshToken: String
    ): BaseResponse<ReissueTokenResponse>
}