package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface Routes{
    // Home
    @Serializable
    data object Home: Routes

    // 지도
    @Serializable
    data object Map: Routes

    // 메뉴판
    @Serializable
    data object MenuFolder: Routes

    // Mypage
    @Serializable
    data object My: Routes

    // Onboarding
    @Serializable
    data object Onboarding: Routes
    @Serializable
    data object Landing: Routes
    @Serializable
    data object Login: Routes
    @Serializable
    data object SignupEmail: Routes
    @Serializable
    data object SignupPassword: Routes
    @Serializable
    data object SignupMealTime: Routes
    @Serializable
    data object SignupVerify: Routes
}
