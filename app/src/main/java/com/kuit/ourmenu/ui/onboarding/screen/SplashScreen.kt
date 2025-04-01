package com.kuit.ourmenu.ui.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.ImageLoader
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.onboarding.viewmodel.SplashViewModel
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.CacheUtil.preloadData
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    imageLoader: ImageLoader,
    viewModel: SplashViewModel = hiltViewModel(),
    toHome: () -> Unit
) {
    val cacheInfoData by viewModel.cacheInfoData.collectAsState()

    preloadData(LocalContext.current, imageLoader, cacheInfoData)
    LaunchedEffect(Unit) {
        delay(1000) // TODO : 추후 수정
        toHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary500Main),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_splash_logo),
            contentDescription = "splash 아이콘",
            modifier =
                Modifier
                    .padding(bottom = 12.dp)
                    .size(140.dp),
        )

        Text(
            text = stringResource(R.string.ourmenu),
            style = ourMenuTypography().pretendard_700_32,
            color = NeutralWhite,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SplashScreenPreview() {
    var showSplash by remember { mutableStateOf(true) }
    SplashScreen(
        imageLoader = ImageLoader.Builder(LocalContext.current).build(),
    ) { showSplash = true }
}
