package com.kuit.ourmenu.ui.signup.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.signup.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.signup.screen.SignupEmailScreen
import com.kuit.ourmenu.ui.signup.screen.SignupMealTimeScreen
import com.kuit.ourmenu.ui.signup.screen.SignupPasswordScreen
import com.kuit.ourmenu.ui.signup.screen.SignupVerifyScreen

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


fun NavGraphBuilder.signupNavGraph(
    navigateBack: () -> Unit,
    navigateOnboardingToHome: () -> Unit,
    navigateToSignupVerify: () -> Unit,
    navigateToSignupMealTime: () -> Unit,
    navigateToSignupPassword: () -> Unit,
    getSignupViewModel: @Composable (NavBackStackEntry) -> SignupViewModel
) {
    composable<Routes.SignupEmail> {
        SignupEmailScreen(
            navigateToVerify = navigateToSignupVerify,
            navigateBack = navigateBack,
//            viewModel = getSignupViewModel(it)
        )
    }
    composable<Routes.SignupVerify> {
        SignupVerifyScreen(
            navigateToPassword = navigateToSignupPassword,
            navigateBack = navigateBack,
            viewModel = getSignupViewModel(it)
        )
    }
    composable<Routes.SignupPassword> {
        SignupPasswordScreen(
            navigateToMealTime = navigateToSignupMealTime,
            navigateBack = navigateBack,
            viewModel = getSignupViewModel(it)
        )
    }
    composable<Routes.SignupMealTime> {
        SignupMealTimeScreen(
            navigateToHome = navigateOnboardingToHome,
            navigateBack = navigateBack,
            viewModel = getSignupViewModel(it)
        )
    }
}