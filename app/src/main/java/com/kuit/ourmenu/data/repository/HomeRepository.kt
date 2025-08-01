package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.model.home.request.HomeAnswerRequest
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

    suspend fun postHomeQuestion() = runCatching {
        homeService.postHomeQuestion().handleBaseResponse().getOrThrow()
    }

    suspend fun postHomeAnswer(answer: String) = runCatching {
        homeService.postHomeAnswer(HomeAnswerRequest(answer))
            .handleBaseResponse()
            .getOrThrow()
    }
}