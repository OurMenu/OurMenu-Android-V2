package com.kuit.ourmenu.ui.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.MainTabRoute

fun NavController.navigateToMy(navOptions: NavOptions) {
    navigate(MainTabRoute.My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    padding: PaddingValues,
    // navigate 이벤트
) {
    composable<MainTabRoute.My> {
        // MyScreen.kt
    }
}