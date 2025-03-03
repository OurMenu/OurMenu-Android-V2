package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface Routes{
    // Home
    @Serializable
    data object Home: Routes

    // 지도

    // 메뉴판

    // Mypage
}
