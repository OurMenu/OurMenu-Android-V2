package com.kuit.ourmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kuit.ourmenu.ui.navigator.MainNavHost
import com.kuit.ourmenu.ui.navigator.MainTab
import com.kuit.ourmenu.ui.navigator.component.MainBottomBar
import com.kuit.ourmenu.ui.navigator.rememberMainNavigator
import com.kuit.ourmenu.ui.onboarding.screen.SplashScreen
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.OurMenuTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.toPersistentList

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
                    Scaffold(
                        bottomBar = {
                            MainBottomBar(
                                modifier = Modifier
                                    .background(NeutralWhite)
                                    .navigationBarsPadding(),
                                visible = navController.shouldShowBottomBar(),
                                tabs = MainTab.entries.toPersistentList(),
                                currentTab = navController.currentTab,
                                onTabSelected = { navController.navigate(it) }
                            )
                        },
                        content = { innerPadding ->
                            MainNavHost(
                                navController = navController,
                                padding = innerPadding
                            )
                        }
                    )
                }
            }
        }
    }
}
