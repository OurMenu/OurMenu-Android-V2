package com.kuit.ourmenu

import android.content.Intent
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
import androidx.lifecycle.lifecycleScope
import coil3.imageLoader
import com.kuit.ourmenu.ui.navigator.MainNavHost
import com.kuit.ourmenu.ui.navigator.MainTab
import com.kuit.ourmenu.ui.navigator.component.MainBottomBar
import com.kuit.ourmenu.ui.navigator.rememberMainNavigator
import com.kuit.ourmenu.ui.onboarding.screen.SplashScreen
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.OurMenuTheme
import com.kuit.ourmenu.utils.auth.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 로그아웃 이벤트 구독
        lifecycleScope.launch {
            tokenManager.logoutEvent.collect {
                // Activity 스택을 모두 클리어하고 MainActivity를 다시 시작
                val intent = Intent(this@MainActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            }
        }

        setContent {
            var showSplash by remember { mutableStateOf(true) }
            val navController = rememberMainNavigator()

            OurMenuTheme {
                if (showSplash) {
                    SplashScreen(
                        imageLoader = imageLoader,
                    ) {
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
