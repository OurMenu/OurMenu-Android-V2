package com.kuit.ourmenu.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.ourmenu.ui.home.screen.HomeScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Home) {
        composable<Routes.Home>{
            HomeScreen(navController = navController)
        }
    }
}