package com.kuit.ourmenu.ui.searchmenu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.MainTabRoute
import com.kuit.ourmenu.ui.searchmenu.screen.SearchMenuScreen

fun NavController.navigateToSearchMenu(navOptions: NavOptions) {
    navigate(MainTabRoute.Map, navOptions)
}

fun NavGraphBuilder.searchMenuNavGraph(
    // navigate 이벤트
) {
    composable<MainTabRoute.Map> {
        SearchMenuScreen()
    }
}