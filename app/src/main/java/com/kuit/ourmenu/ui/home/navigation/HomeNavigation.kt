package com.kuit.ourmenu.ui.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.navigator.MainTabRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    navigateToMenuInfo: (Long) -> Unit,
    navigateToAddMenu: () -> Unit,
) {
    composable<MainTabRoute.Home> {
        HomeScreen(
            padding = padding,
            onNavigateToMenuInfo = navigateToMenuInfo,
            onNavigateToAddMenu = navigateToAddMenu,
        )
    }
}