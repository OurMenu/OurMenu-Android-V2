package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupMealTimeScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Landing) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }
        composable<Routes.Landing> {
            LandingScreen(navController = navController)
        }
        composable<Routes.Login> {
            LoginScreen(navController = navController)
        }
        navigation<Routes.Signup>(startDestination = Routes.SignupEmail) {
            composable<Routes.SignupEmail> {
                val viewModel = hiltViewModel<SignupViewModel>(
                    navController.getBackStackEntry(Routes.Signup)
                )
                SignupEmailScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupPassword> {
                val viewModel = hiltViewModel<SignupViewModel>(
                    navController.getBackStackEntry(Routes.Signup)
                )
                SignupPasswordScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupMealTime> {
                val viewModel = hiltViewModel<SignupViewModel>(
                    navController.getBackStackEntry(Routes.Signup)
                )
                SignupMealTimeScreen(navController = navController, viewModel = viewModel)
            }
            composable<Routes.SignupVerify> {
                val viewModel = hiltViewModel<SignupViewModel>(
                    navController.getBackStackEntry(Routes.Signup)
                )
                SignupVerifyScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}