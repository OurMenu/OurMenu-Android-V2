package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupNicknameScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Home) {
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
    }
}