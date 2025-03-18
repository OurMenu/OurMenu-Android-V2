package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuScreen
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderDetailScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderScreen
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupNicknameScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.MenuFolder) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable<Routes.Landing> {
            LandingScreen(navController = navController)
        }
        composable<Routes.Login> {
            LoginScreen(navController = navController)
        }
        composable<Routes.SignupEmail> {
            SignupEmailScreen(navController = navController)
        }
        composable<Routes.SignupPassword> {
            SignupPasswordScreen(navController = navController)
        }
        composable<Routes.SignupNickname> {
            SignupNicknameScreen(navController = navController)
        }
        composable<Routes.SignupVerify> {
            SignupVerifyScreen(navController = navController)
        }

        // 메뉴판
        composable<Routes.MenuFolder> {
            MenuFolderScreen(navController = navController)
        }
        composable<Routes.MenuFolderDetail> {
            MenuFolderDetailScreen(navController = navController)
        }

        // 메뉴 추가
        composable<Routes.AddMenu> {
            AddMenuScreen(navController = navController)
        }
    }
}