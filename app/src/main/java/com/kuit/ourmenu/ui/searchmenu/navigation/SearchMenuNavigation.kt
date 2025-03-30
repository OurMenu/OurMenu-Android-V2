package com.kuit.ourmenu.ui.searchmenu.navigation

import androidx.compose.foundation.layout.PaddingValues
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
    padding: PaddingValues,
    // navigate 이벤트
) {
    composable<MainTabRoute.Map> {
        SearchMenuScreen()
    }
}