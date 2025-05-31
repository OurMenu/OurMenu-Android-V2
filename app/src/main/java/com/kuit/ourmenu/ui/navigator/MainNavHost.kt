package com.kuit.ourmenu.ui.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.kuit.ourmenu.ui.addmenu.navigation.addMenuNavGraph
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuScreen
import com.kuit.ourmenu.ui.home.navigation.homeNavGraph
import com.kuit.ourmenu.ui.menuFolder.navigation.menuFolderNavGraph
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderAllMenuScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderDetailScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoDefaultScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoMapScreen
import com.kuit.ourmenu.ui.my.navigation.myNavGraph
import com.kuit.ourmenu.ui.onboarding.navigation.onboardingNavGraph
import com.kuit.ourmenu.ui.searchmenu.navigation.searchMenuNavGraph
import com.kuit.ourmenu.ui.signup.navigation.signupNavGraph
import com.kuit.ourmenu.ui.signup.viewmodel.SignupViewModel

@Composable
fun MainNavHost(
    navController: MainNavController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController.navController,
        startDestination = navController.startDestination
    ) {
        onboardingNavGraph(
            navigateBack = navController::navigateUp,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToLogin = navController::navigateToLogin,
            navigateToSignupEmail = navController::navigateToSignupEmail,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
        )

        signupNavGraph(
            navigateBack = navController::navigateUp,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToSignupVerify = navController::navigateToSignupVerify,
            navigateToSignupPassword = navController::navigateToSignupPassword,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
            getSignupViewModel = { navBackStackEntry ->
                val parent = remember(navBackStackEntry) {
                    navController.navController.getBackStackEntry(Routes.SignupEmail)
                }
                hiltViewModel<SignupViewModel>(parent)
            }
        )

        homeNavGraph(
            padding = padding,
        )

        menuFolderNavGraph(
            navigateBack = navController::navigateUp,
            navigateToMenuFolderDetail = navController::navigateToMenuFolderDetail,
            navigateToMenuFolderAllMenu = navController::navigateToMenuFolderAllMenu,
            navigateToMenuInfo = navController::navigateToMenuInfo,
            navigateToAddMenu = navController::navigateToAddMenu,
        )

        addMenuNavGraph(
            navigateBack = navController::navigateUp,
            navigateToAddMenuInfo = navController::navigateToAddMenuInfo
        )

        searchMenuNavGraph(
            padding = padding,
        )

        myNavGraph(
            padding = padding,
        )

        // 메뉴판
        composable<Routes.MenuFolderDetail> {
            val menuFolderId = it.toRoute<Routes.MenuFolderDetail>().menuFolderId
            MenuFolderDetailScreen(
                menuFolderId = menuFolderId,
                onNavigateBack = navController::navigateUp,
                onNavigateToMenuInfo = navController::navigateToMenuInfo,
                onNavigateToAddMenu = navController::navigateToAddMenu
            )
        }
        composable<Routes.MenuFolderAllMenu> {
            MenuFolderAllMenuScreen(
                onNavigateBack = navController::navigateUp,
                // TODO: 나머지 navigate 작성
            )
        }

        // 메뉴
        composable<Routes.MenuInfo> {
            val menuId = it.toRoute<Routes.MenuInfo>().menuId
            MenuInfoDefaultScreen(
                menuId = menuId,
                onNavigateBack = navController::navigateUp,
//                onNavigateToMap = navController::navigateToMenuInfoMap
            )
        }
        composable<Routes.MenuInfoMap> {
            MenuInfoMapScreen(navController = navController.navController)
        }

        // 메뉴 추가
        composable<Routes.AddMenu> {
            AddMenuScreen(
                onNavigateToAddMenuInfo = navController::navigateToAddMenuInfo
            )
        }
    }
}