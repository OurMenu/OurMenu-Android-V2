package com.kuit.ourmenu.ui.menuinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoDefaultScreen
import com.kuit.ourmenu.ui.navigator.Routes

fun NavController.navigateToMenuInfo(menuId: Int) {
    navigate(Routes.MenuInfo(menuId))
}

fun NavGraphBuilder.menuInfoNavGraph(
    navigateBack: () -> Unit,
    navigateToMenuFolderDetail: (Int) -> Unit,
    navigateToMenuInfoMap: () -> Unit
) {
    composable<Routes.MenuInfo> {
        val menuId = it.toRoute<Routes.MenuInfo>().menuId
        MenuInfoDefaultScreen(
            menuId = menuId,
            onNavigateBack = navigateBack,
            onNavigateToMenuFolderDetail = navigateToMenuFolderDetail,
//            onNavigateToMap = navigateToMenuInfoMap
        )
    }

//    composable<Routes.MenuInfoMap> {
//        MenuInfoMapScreen(
//            onNavigateBack = navigateBack
//        )
//    }
}