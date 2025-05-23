package com.kuit.ourmenu.ui.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.my.screen.EditMyMealTimeRoute
import com.kuit.ourmenu.ui.my.screen.MyRoute
import com.kuit.ourmenu.ui.navigator.MainTabRoute
import com.kuit.ourmenu.ui.navigator.Routes

fun NavController.navigateToMy(navOptions: NavOptions) {
    navigate(MainTabRoute.My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    padding: PaddingValues,
    navigateToLanding: () -> Unit = {},
    navigateToEdit: () -> Unit = {},
    navigateToBack: () -> Unit = {},
) {
    composable<MainTabRoute.My> {
        MyRoute(
            padding = padding,
            navigateToEdit = navigateToEdit,
            navigateToLanding = navigateToLanding,
        )
    }

    composable<Routes.EditMyMealTime> {
        EditMyMealTimeRoute(
            padding = padding,
            navigateToBack = navigateToBack,
        )
    }
}