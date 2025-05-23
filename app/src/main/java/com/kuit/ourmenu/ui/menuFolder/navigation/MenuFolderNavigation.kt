package com.kuit.ourmenu.ui.menuFolder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderAllMenuScreen
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

fun NavController.navigateToAddMenu() {
    navigate(Routes.AddMenu)
}

fun NavController.navigateToAddMenuInfo() {
    navigate(Routes.AddMenuInfo)
}

fun NavGraphBuilder.menuFolderNavGraph(
    navigateBack: () -> Unit,
    navigateToMenuFolderDetail: (Int) -> Unit,
    navigateToMenuFolderAllMenu: () -> Unit,
//    navigateToMenuInfo: () -> Unit,
    navigateToAddMenu: () -> Unit,
    navigateToAddMenuInfo: () -> Unit
) {
    composable<MainTabRoute.MenuFolder> {
        MenuFolderScreen(
            onNavigateToDetail = navigateToMenuFolderDetail,
            onNavigateToAllMenu = navigateToMenuFolderAllMenu,
//            onNavigateToAddMenu = {},
        )

        composable<Routes.MenuFolderDetail> {
            val menuFolderId = it.toRoute<Routes.MenuFolderDetail>().menuFolderId
            MenuFolderDetailScreen(
                menuFolderId = menuFolderId,
//                onNavigateToMenuInfo = navigateToMenuInfo,
                onNavigateBack = navigateBack,
                onNavigateToAddMenu = navigateToAddMenu
            )
        }

        composable<Routes.MenuFolderAllMenu> {
            MenuFolderAllMenuScreen(
                onNavigateBack = navigateBack,
//                onNavigateToMenuInfo = navigateToMenuFolderDetail, // TODO: Menu Info로 화면 이동 구현
//                onNavigateToMenuInfoMap = navigateToMenuFolderDetail, // TODO: Map으로 화면 이동 구현
//                onNavigateToAddMenu = {}, // TODO: AddMenu로 화면 이동 구현
            )
        }
    }
}