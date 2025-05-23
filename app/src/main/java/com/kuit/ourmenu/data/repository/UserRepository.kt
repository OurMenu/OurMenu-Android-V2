package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.model.user.request.ChangeMealTimeRequest
import com.kuit.ourmenu.data.model.user.request.ChangePasswordRequest
import com.kuit.ourmenu.data.oauth.KakaoRepository
import com.kuit.ourmenu.data.service.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userService: UserService,
    private val kakaoRepository: KakaoRepository
) {

    suspend fun sendTemporaryPassword(
        email: String
    ) = runCatching {
        userService.sendTemporaryPassword(email).handleBaseResponse().getOrThrow()
    }

    suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ) = runCatching {
        userService.changePassword(
            ChangePasswordRequest(currentPassword, newPassword)
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun updateMealTimes(
        newMealTimes: List<Int>
    ) = runCatching {
        userService.updateMealTimes(
            ChangeMealTimeRequest(newMealTimes)
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getUserInfo() = runCatching {
        userService.getUserInfo().handleBaseResponse().getOrThrow()
    }

    suspend fun deleteUser() = runCatching {
        kakaoRepository.unlink { error ->
            throw error
        }
        userService.deleteUser().handleBaseResponse().getOrThrow()
    }
}