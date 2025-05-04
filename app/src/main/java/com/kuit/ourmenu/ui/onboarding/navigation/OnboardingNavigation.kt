package com.kuit.ourmenu.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.MainTabRoute
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.screen.LandingRoute
import com.kuit.ourmenu.ui.onboarding.screen.LandingScreen
import com.kuit.ourmenu.ui.onboarding.screen.LoginRoute
import com.kuit.ourmenu.ui.onboarding.screen.LoginScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupEmailScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupMealTimeScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupPasswordScreen
import com.kuit.ourmenu.ui.onboarding.screen.signup.SignupVerifyScreen
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel

fun NavController.navigateToLogin() {
    navigate(Routes.Login)
}

fun NavController.navigateToSignupEmail() {
    navigate(Routes.SignupEmail)
}

fun NavController.navigateToSignupPassword() {
    navigate(Routes.SignupPassword)
}

fun NavController.navigateToSignupMealTime() {
    navigate(Routes.SignupMealTime)
}

fun NavController.navigateToSignupVerify() {
    navigate(Routes.SignupVerify)
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
    navigateToSignupVerify: () -> Unit,
    navigateToSignupMealTime: () -> Unit,
    navigateToSignupPassword: () -> Unit,
    viewModel: SignupViewModel,
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
    composable<Routes.SignupEmail> {
        SignupEmailScreen(
            navigateToVerify = navigateToSignupVerify,
            navigateBack = navigateBack,
            viewModel = viewModel
        )
    }
    composable<Routes.SignupVerify> {
        SignupVerifyScreen(
            navigateToPassword = navigateToSignupPassword,
            navigateBack = navigateBack,
            viewModel = viewModel
        )
    }
    composable<Routes.SignupPassword> {
        SignupPasswordScreen(
            navigateToMealTime = navigateToSignupMealTime,
            navigateBack = navigateBack,
            viewModel = viewModel
        )
    }
    composable<Routes.SignupMealTime> {
        SignupMealTimeScreen(
            navigateToHome = navigateOnboardingToHome,
            navigateBack = navigateBack,
            viewModel = viewModel
        )
    }
}