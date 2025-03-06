package com.kuit.ourmenu.ui.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.component.BottomFullWidthBorderButton
import com.kuit.ourmenu.ui.onboarding.viewmodel.LoginViewModel
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun LandingScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(bottom = 18.dp),
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_top_bar_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 120.dp)
                    .size(78.dp),
            )

            Text(
                text = stringResource(R.string.ourmenu),
                style = ourMenuTypography().pretendard_700_32,
                color = Primary500Main,
                modifier = Modifier.padding(top = 4.dp),
            )

            Text(
                text = stringResource(R.string.landing_introduce_title),
                style = ourMenuTypography().pretendard_600_18,
                color = Neutral900,
                modifier = Modifier.padding(top = 72.dp),
            )
            Text(
                text = stringResource(R.string.landing_introduce_content),
                style = ourMenuTypography().pretendard_400_14,
                color = Neutral700,
                modifier = Modifier.padding(top = 2.dp),
            )

            Spacer(modifier = Modifier.height(44.dp))

            BottomFullWidthButton(
                modifier = Modifier,
                containerColor = Primary500Main,
                contentColor = NeutralWhite,
                text = stringResource(R.string.login)
            ) {
                navController.navigate(route = Routes.Login)
            }

            Spacer(modifier = Modifier.height(16.dp))

            BottomFullWidthBorderButton {
                navController.navigate(route = Routes.SignupEmail)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    thickness = 1.dp,
                    color = Neutral300
                )

                Box(
                    modifier = Modifier
                        .width(75.dp)
                        .height(38.dp)
                        .background(NeutralWhite)
                        .align(Alignment.Center),
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "소셜 로그인",
                    style = ourMenuTypography().pretendard_500_12.copy(
                        color = Neutral500
                    ),
                    textAlign = TextAlign.Center,
                )
            }

            Image(
                painter = painterResource(R.drawable.img_kakao_login_medium_wide),
                contentDescription = "kakao login",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        Row(
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            Text(
                text = stringResource(R.string.ourmenu),
                style = ourMenuTypography().pretendard_700_14,
                color = Primary500Main,
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.copy_right),
                style = ourMenuTypography().pretendard_400_12,
                color = Neutral500,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LandingScreenPreview() {
    val navController = rememberNavController()

    LandingScreen(navController)
}
