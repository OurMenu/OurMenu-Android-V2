package com.kuit.ourmenu.ui.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            modifier = Modifier.padding(top = 112.dp).size(78.dp),
        )

        Text(
            text = stringResource(R.string.ourmenu),
            style = ourMenuTypography().pretendard_700_32,
            color = Primary500Main,
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
            modifier = Modifier.padding(top = 8.dp),
        )

        Spacer(modifier = Modifier.height(44.dp))

        BottomFullWidthButton(modifier = Modifier, containerColor = Primary500Main, contentColor = NeutralWhite, text = stringResource(R.string.login)) {
            navController.navigate(route = Routes.Login)
        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomFullWidthBorderButton {
            navController.navigate(route = Routes.SignupEmail)
        }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = 18.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Row {
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
