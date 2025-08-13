package com.kuit.ourmenu.ui.navigator

import kotlinx.serialization.Serializable

sealed interface MainTabRoute : Routes {
    @Serializable
    data object Home : MainTabRoute
    @Serializable
    data object Map : MainTabRoute
    @Serializable
    data object MenuFolder : MainTabRoute
    @Serializable
    data object My : MainTabRoute
}