package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.addmenu.screen.AddMenuScreen
import androidx.navigation.compose.navigation
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderAllMenuScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderDetailScreen
import com.kuit.ourmenu.ui.menuFolder.screen.MenuFolderScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoDefaultScreen
import com.kuit.ourmenu.ui.menuinfo.screen.MenuInfoMapScreen
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupMealTimeScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    val viewModel = hiltViewModel<SignupViewModel>()

    NavHost(navController, startDestination = Routes.Onboarding) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }

        navigation<Routes.Onboarding>(startDestination = Routes.Landing) {

            composable<Routes.Landing> {
                LandingScreen(navController = navController)
            }
            composable<Routes.Login> {
                LoginScreen(navController = navController)
            }
            composable<Routes.SignupEmail> {
                SignupEmailScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupPassword> {
                SignupPasswordScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupMealTime> {
                SignupMealTimeScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupVerify> {
                SignupVerifyScreen(navController = navController, viewModel = viewModel)
            }
        }

        // 메뉴판
        composable<Routes.MenuFolder> {
            MenuFolderScreen(navController = navController)
        }
        composable<Routes.MenuFolderDetail> {
            MenuFolderDetailScreen(navController = navController)
        }
        composable<Routes.MenuFolderAllMenu> {
            MenuFolderAllMenuScreen(navController = navController)
        }

        // 메뉴
        composable<Routes.MenuInfo> {
            MenuInfoDefaultScreen(navController = navController)
        }
        composable<Routes.MenuInfoMap> {
            MenuInfoMapScreen(navController = navController)
        }

        // 메뉴 추가
        composable<Routes.AddMenu> {
            AddMenuScreen(navController = navController)
        }
    }
}