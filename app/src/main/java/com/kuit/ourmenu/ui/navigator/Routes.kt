package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface Routes{

    // 메뉴판
    @Serializable
    data object MenuFolder: Routes
    @Serializable
    data class MenuFolderDetail(val menuFolderId: Int): Routes
    @Serializable
    data object MenuFolderAllMenu: Routes

    // 메뉴
    @Serializable
    data object MenuInfo: Routes
    @Serializable
    data object MenuInfoMap: Routes

    // 메뉴 추가
    @Serializable
    data object AddMenu: Routes

    // Mypage

    // Onboarding
    @Serializable
    data object Signup: Routes
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
