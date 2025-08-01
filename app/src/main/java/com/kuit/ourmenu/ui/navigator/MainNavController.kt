package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kuit.ourmenu.ui.addmenu.navigation.navigateToAddMenuInfo
import com.kuit.ourmenu.ui.home.navigation.navigateToHome
import com.kuit.ourmenu.ui.menuFolder.navigation.navigateToAddMenu
import com.kuit.ourmenu.ui.menuFolder.navigation.navigateToMenuFolder
import com.kuit.ourmenu.ui.menuFolder.navigation.navigateToMenuFolderAllMenu
import com.kuit.ourmenu.ui.menuFolder.navigation.navigateToMenuFolderDetail
import com.kuit.ourmenu.ui.menuinfo.navigation.navigateToMenuInfo
import com.kuit.ourmenu.ui.my.navigation.navigateToEditMyMealTime
import com.kuit.ourmenu.ui.my.navigation.navigateToMy
import com.kuit.ourmenu.ui.onboarding.navigation.navigateOnboardingToHome
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToLanding
import com.kuit.ourmenu.ui.onboarding.navigation.navigateToLogin
import com.kuit.ourmenu.ui.searchmenu.navigation.navigateToSearchMenu
import com.kuit.ourmenu.ui.signup.navigation.navigateToSignupEmail
import com.kuit.ourmenu.ui.signup.navigation.navigateToSignupMealTime
import com.kuit.ourmenu.ui.signup.navigation.navigateToSignupPassword
import com.kuit.ourmenu.ui.signup.navigation.navigateToSignupVerify

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
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.MAP -> navController.navigateToSearchMenu(navOptions)
            MainTab.MENU_FOLDER -> navController.navigateToMenuFolder(navOptions)
            MainTab.MY -> navController.navigateToMy(navOptions)
        }
    }

    // Back Pressed
    fun navigateUp() {
        navController.navigateUp()
    }

    // Onboarding
    fun navigateToLanding() {
        navController.navigateToLanding()
    }

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

    // Menu Folder
    fun navigateToMenuFolderDetail(menuFolderId: Long) {
        navController.navigateToMenuFolderDetail(menuFolderId)
    }

    fun navigateToMenuFolderAllMenu() {
        navController.navigateToMenuFolderAllMenu()
    }

    // My
    fun navigateToEditMyMealTime(selectedTimes: List<Int>) {
        navController.navigateToEditMyMealTime(selectedTimes)
    }

    // Add Menu
    fun navigateToAddMenu() {
        navController.navigateToAddMenu()
    }

    fun navigateToAddMenuInfo() {
        navController.navigateToAddMenuInfo()
    }

    // Menu Info
    fun navigateToMenuInfo(menuId: Int) {
        navController.navigateToMenuInfo(menuId)
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