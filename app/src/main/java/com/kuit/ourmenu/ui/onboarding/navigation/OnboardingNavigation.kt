package com.kuit.ourmenu.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.MainTabRoute
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.screen.LandingRoute
import com.kuit.ourmenu.ui.onboarding.screen.LoginRoute

fun NavController.navigateToLogin() {
    navigate(Routes.Login)
}

fun NavController.navigateOnboardingToHome() {
    navigate(MainTabRoute.Home) {
        popUpTo(Routes.Landing) {
            inclusive = true
        }
    }
}


fun NavGraphBuilder.onboardingNavGraph(
    navigateBack: () -> Unit,
    navigateOnboardingToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToSignupEmail: () -> Unit,
    navigateToSignupMealTime: () -> Unit,
) {
    composable<Routes.Landing> {
        LandingRoute(
            navigateToHome = navigateOnboardingToHome,
            navigateToLogin = navigateToLogin,
            navigateToSignupEmail = navigateToSignupEmail,
            navigateToSignupMealTime = navigateToSignupMealTime,
        )
    }
    composable<Routes.Login> {
        LoginRoute(
            navigateToHome = navigateOnboardingToHome,
            navigateBack = navigateBack,
            navigateToSignupEmail = navigateToSignupEmail,
        )
    }

}