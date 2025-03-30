package com.kuit.ourmenu.ui.menuFolder.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderScreen
import com.kuit.ourmenu.ui.navigator.MainTabRoute

fun NavController.navigateToMenuFolder(navOptions: NavOptions) {
    navigate(MainTabRoute.MenuFolder, navOptions)
}

fun NavGraphBuilder.menuFolderNavGraph(
    padding: PaddingValues,
    // navigate 이벤트
) {
    composable<MainTabRoute.MenuFolder> {
        MenuFolderScreen(
            // navigate 이벤트 + 기타 이벤트
        )
    }
}