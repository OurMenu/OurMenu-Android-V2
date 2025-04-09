package com.kuit.ourmenu.ui.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuScreen
import com.kuit.ourmenu.ui.home.navigation.homeNavGraph
import com.kuit.ourmenu.ui.menuFolder.navigation.menuFolderNavGraph
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderAllMenuScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderDetailScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoDefaultScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoMapScreen
import com.kuit.ourmenu.ui.my.navigation.myNavGraph
import com.kuit.ourmenu.ui.onboarding.navigation.onboardingNavGraph
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.searchmenu.navigation.searchMenuNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: MainNavController,
    padding : PaddingValues
) {
    val signupViewModel = hiltViewModel<SignupViewModel>()

    NavHost(
        navController = navController.navController,
        startDestination = navController.startDestination
    ) {

        onboardingNavGraph(
            viewModel = signupViewModel,
            navigateBack = navController::navigateUp,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToLogin = navController::navigateToLogin,
            navigateToSignupEmail = navController::navigateToSignupEmail,
            navigateToSignupVerify = navController::navigateToSignupVerify,
            navigateToSignupPassword = navController::navigateToSignupPassword,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
        )

        homeNavGraph(
            padding = padding,
        )

        menuFolderNavGraph(
            navigateToMenuFolderDetail = navController::navigateToMenuFolderDetail,
            navigateToMenuFolderAllMenu = navController::navigateToMenuFolderAllMenu,
        )

        searchMenuNavGraph(
            padding = padding,
        )

        myNavGraph(
            padding = padding,
        )

        // 메뉴판
        composable<Routes.MenuFolderDetail> {
            MenuFolderDetailScreen(navController = navController.navController)
        }
        composable<Routes.MenuFolderAllMenu> {
            MenuFolderAllMenuScreen(navController = navController.navController)
        }

        // 메뉴
        composable<Routes.MenuInfo> {
            MenuInfoDefaultScreen(navController = navController.navController)
        }
        composable<Routes.MenuInfoMap> {
            MenuInfoMapScreen(navController = navController.navController)
        }

        // 메뉴 추가
        composable<Routes.AddMenu> {
            AddMenuScreen(navController = navController.navController)
        }
    }
}