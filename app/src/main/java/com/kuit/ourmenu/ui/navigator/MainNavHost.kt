package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.home.navigation.homeNavGraph
import com.kuit.ourmenu.ui.home.screen.HomeScreen
import com.kuit.ourmenu.ui.menuFolder.navigation.menuFolderNavGraph
import com.kuit.ourmenu.ui.my.navigation.myNavGraph
import com.kuit.ourmenu.ui.onboarding.navigation.onboardingNavGraph
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.searchmenu.navigation.searchMenuNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: MainNavController,
) {
    val viewModel = hiltViewModel<SignupViewModel>()

    NavHost(
        navController = navController.navController,
        startDestination = Routes.Landing
    ) {
        composable<Routes.Home> {
            HomeScreen()
        }

        onboardingNavGraph(
            padding = androidx.compose.foundation.layout.PaddingValues(0.dp),
            navigateBack = navController::navigateUp,
            navigateOnboardingToHome = navController::navigateOnboardingToHome,
            navigateToLogin = navController::navigateToLogin,
            navigateToSignupEmail = navController::navigateToSignupEmail,
            navigateToSignupVerify = navController::navigateToSignupVerify,
            navigateToSignupPassword = navController::navigateToSignupPassword,
            navigateToSignupMealTime = navController::navigateToSignupMealTime,
            getBackStackSignupViewModel = { navBackStackEntry ->
                navController.navController.previousBackStackEntry?.let { previousEntry ->
                    hiltViewModel<SignupViewModel>(previousEntry)
                } ?: hiltViewModel(navBackStackEntry)
            })

        homeNavGraph()

        menuFolderNavGraph()

        searchMenuNavGraph()

        myNavGraph()
    }
}