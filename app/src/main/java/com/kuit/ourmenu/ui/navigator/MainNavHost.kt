package com.kuit.ourmenu.ui.navigator

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.kuit.ourmenu.ui.home.navigation.homeNavGraph
import com.kuit.ourmenu.ui.menuFolder.navigation.menuFolderNavGraph
import com.kuit.ourmenu.ui.my.navigation.myNavGraph
import com.kuit.ourmenu.ui.onboarding.navigation.onboardingNavGraph
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.searchmenu.navigation.searchMenuNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: MainNavController,
    padding : PaddingValues
) {
    val signupViewModel = hiltViewModel<SignupViewModel>()

    NavHost(
        navController = navController.navController,
        startDestination = MainTabRoute.Home
    ) {

        onboardingNavGraph(
            viewModel = signupViewModel,
            navigateBack = navController::navigateUp,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToLogin = navController::navigateToLogin,
            navigateToSignupEmail = navController::navigateToSignupEmail,
            navigateToSignupVerify = navController::navigateToSignupVerify,
            navigateToSignupPassword = navController::navigateToSignupPassword,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
        )

        homeNavGraph(
            padding = padding,
        )

        menuFolderNavGraph(
            padding = padding,
        )

        searchMenuNavGraph(
            padding = padding,
        )

        myNavGraph(
            padding = padding,
        )
    }
}