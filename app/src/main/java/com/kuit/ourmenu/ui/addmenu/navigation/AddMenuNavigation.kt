package com.kuit.ourmenu.ui.addmenu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuInfoScreen
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuScreen
import com.kuit.ourmenu.ui.navigator.Routes


fun NavController.navigateToAddMenuInfo() {
    navigate(Routes.AddMenuInfo)
}

fun NavGraphBuilder.addMenuNavGraph(
    navigateBack: () -> Unit,
    navigateToAddMenuInfo: () -> Unit
) {
    composable<Routes.AddMenu> {
        AddMenuScreen(
            onNavigateToAddMenuInfo = navigateToAddMenuInfo
        )
    }

    composable<Routes.AddMenuInfo> {
        AddMenuInfoScreen()
    }
}