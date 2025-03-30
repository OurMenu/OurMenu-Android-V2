package com.kuit.ourmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kuit.ourmenu.ui.navigator.MainNavHost
import com.kuit.ourmenu.ui.navigator.rememberMainNavigator
import com.kuit.ourmenu.ui.onboarding.screen.SplashScreen
import com.kuit.ourmenu.ui.theme.OurMenuTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplash by remember { mutableStateOf(true) }
            val navController = rememberMainNavigator()

            OurMenuTheme {
                if (showSplash) {
                    SplashScreen {
                        showSplash = false
                    }
                } else {
                    // TODO: MainNavigation 추가하기
                    MainNavHost(navController = navController)
                }
            }
        }
    }
}
