package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.auth.response.TemporaryPasswordResponse
import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.user.request.ChangePasswordRequest
import com.kuit.ourmenu.data.model.user.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {

    @POST("api/emails/temporary-password")
    suspend fun sendTemporaryPassword(
        @Body email: String
    ): BaseResponse<TemporaryPasswordResponse>

    @PATCH("api/users/password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): BaseResponse<Unit>

    @PATCH("api/users/meal-times")
    suspend fun updateMealTimes(
        @Body mealTimes: List<Int>
    ): BaseResponse<Unit>

    @GET("api/users")
    suspend fun getUserInfo(): BaseResponse<UserInfoResponse> // TODO : 리스폰스 변경에 따라 수정해야함
}