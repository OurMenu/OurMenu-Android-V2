package com.kuit.ourmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.ui.navigator.MainNavGraph
import com.kuit.ourmenu.ui.onboarding.screen.SplashScreen
import com.kuit.ourmenu.ui.theme.OurMenuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplash by remember { mutableStateOf(true) }
            val navController = rememberNavController()

            OurMenuTheme {
                if (showSplash) {
                    SplashScreen {
                        showSplash = false
                    }
                } else {
                    // TODO: MainNavigation 추가하기
                    MainNavGraph(navController = navController)
                }
            }
        }
    }
}
