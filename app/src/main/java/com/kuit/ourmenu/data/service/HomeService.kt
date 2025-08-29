package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.home.request.HomeAnswerRequest
import com.kuit.ourmenu.data.model.home.response.HomeQuestionResponse
import com.kuit.ourmenu.data.model.home.response.HomeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeService {
    @GET("api/home")
    suspend fun getHome(): BaseResponse<HomeResponse>

    @POST("/api/home/questions")
    suspend fun postHomeQuestion(): BaseResponse<HomeQuestionResponse>

    @POST("/api/home/questions/answers")
    suspend fun postHomeAnswer(
        @Body answerRequest: HomeAnswerRequest
    ): BaseResponse<Unit>
}