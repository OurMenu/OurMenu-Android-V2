package com.kuit.ourmenu.ui.menuFolder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderDetailScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderScreen
import com.kuit.ourmenu.ui.navigator.MainTabRoute
import com.kuit.ourmenu.ui.navigator.Routes

fun NavController.navigateToMenuFolder(navOptions: NavOptions) {
    navigate(MainTabRoute.MenuFolder, navOptions)
}

// 이동 이벤트 (menuFolderId 전달)
fun NavController.navigateToMenuFolderDetail(menuFolderId: Int) {
    navigate(Routes.MenuFolderDetail(menuFolderId))
}

fun NavController.navigateToMenuFolderAllMenu() {
    navigate(Routes.MenuFolderAllMenu)
}

fun NavGraphBuilder.menuFolderNavGraph(
    navigateBack: () -> Unit,
    navigateToMenuFolderDetail: (Int) -> Unit,
    navigateToMenuFolderAllMenu: () -> Unit,
//    navigateToMenuInfo: () -> Unit,
) {
    composable<MainTabRoute.MenuFolder> {
        MenuFolderScreen(
            onNavigateToDetail = navigateToMenuFolderDetail,
            onNavigateToAllMenu = navigateToMenuFolderAllMenu,
//            onNavigateToAddMenu = {},
        )

        composable<Routes.MenuFolderDetail> {
            MenuFolderDetailScreen(
//                onNavigateToMenuInfo = navigateToMenuInfo,
                onNavigateBack = navigateBack,
            )
        }

        composable<Routes.MenuFolderAllMenu> {
//            MenuFolderAllMenuScreen()
        }
    }
}