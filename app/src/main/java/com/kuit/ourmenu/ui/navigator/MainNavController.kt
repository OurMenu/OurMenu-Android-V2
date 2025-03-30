package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kuit.ourmenu.ui.onboarding.navigation.navigateOnboardingToHome
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToLogin
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupEmail
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupMealTime
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupPassword
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToSignupVerify

class MainNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Routes.Landing

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            currentDestination?.route == tab.route::class.qualifiedName
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigate(Routes.Home)
            MainTab.MAP -> navController.navigate(Routes.Map)
            MainTab.MENU_FOLDER -> navController.navigate(Routes.MenuFolder)
            MainTab.MY -> navController.navigate(Routes.My)
        }
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigate(Routes.Home, navOptions)
    }

    fun navigateToMap(navOptions: NavOptions) {
        navController.navigate(Routes.Map, navOptions)
    }

    fun navigateToMenuFolder(navOptions: NavOptions) {
        navController.navigate(Routes.MenuFolder, navOptions)
    }

    fun navigateToMy(navOptions: NavOptions) {
        navController.navigate(Routes.My, navOptions)
    }

    // Onboarding
    fun navigateToLogin() {
        navController.navigateToLogin()
    }

    fun navigateToSignupEmail() {
        navController.navigateToSignupEmail()
    }

    fun navigateToSignupPassword() {
        navController.navigateToSignupPassword()
    }

    fun navigateToSignupMealTime() {
        navController.navigateToSignupMealTime()
    }

    fun navigateToSignupVerify() {
        navController.navigateToSignupVerify()
    }

    fun navigateOnboardingToHome() {
        navController.navigateOnboardingToHome()
    }
    fun popBackStack() {
        navController.popBackStack()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean = MainTab.contains {
        currentDestination?.route?.startsWith(it::class.qualifiedName!!) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavController = remember(navController) {
    MainNavController(navController)
}