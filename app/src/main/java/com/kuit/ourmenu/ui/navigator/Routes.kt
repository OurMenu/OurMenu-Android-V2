package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface Routes{
    // Home
    @Serializable
    data object Home: Routes

    // 지도

    // 메뉴판
    @Serializable
    data object MenuFolder: Routes

    // 메뉴 추가
    @Serializable
    data object AddMenu: Routes

    // Mypage

    // Onboarding
    @Serializable
    data object Landing: Routes
    @Serializable
    data object Login: Routes
    @Serializable
    data object SignupEmail: Routes
    @Serializable
    data object SignupPassword: Routes
    @Serializable
    data object SignupNickname: Routes
    @Serializable
    data object SignupVerify: Routes
}
