package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.onboarding.navigation.navigateOnboardingToHome
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToLogin
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupEmail
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupMealTime
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupVerify
import com.kuit.ourmenu.ui.onboarding.navigation.onboardingNavGraph
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    val viewModel = hiltViewModel<SignupViewModel>()

    NavHost(navController, startDestination = Routes.Onboarding) {
        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }

        onboardingNavGraph(
            padding = androidx.compose.foundation.layout.PaddingValues(0.dp),
            viewModel = viewModel,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToLogin = navController::navigateToLogin,
            navigateToSignupEmail = navController::navigateToSignupEmail,
            navigateToSignupVerify = navController::navigateToSignupVerify,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
        )
    }
}