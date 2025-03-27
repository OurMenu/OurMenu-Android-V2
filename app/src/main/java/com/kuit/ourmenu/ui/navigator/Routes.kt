package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface Routes{
    // Home
    @Serializable
    data object Home: Routes

    // AddMenu
    @Serializable
    data object AddMenu: Routes

    // 지도

    // 메뉴판

    // Mypage

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
