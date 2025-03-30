package com.kuit.ourmenu.ui.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupMealTimeScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel

fun NavGraphBuilder.onboardingNavGraph(
    padding: PaddingValues,
    viewModel : SignupViewModel,
    navController: NavController
) {

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