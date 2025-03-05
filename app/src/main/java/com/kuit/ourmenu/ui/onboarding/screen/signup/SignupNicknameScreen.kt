package com.kuit.ourmenu.ui.onboarding.screen.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.navigator.Routes
import com.kuit.ourmenu.ui.onboarding.component.LoginTextField
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SignupNicknameScreen(
    navController: NavController
) {

    var nickname by rememberSaveable { mutableStateOf("") }
    var enable by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = { OnboardingTopAppBar(
            onBackClick = {
                navController.navigateUp()
            }
        ) },
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 92.dp)
            ) {
                Text(
                    text = stringResource(R.string.input_nickname),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier
                )

                Text(
                    text = stringResource(R.string.input_nickname_description),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 4.dp),
                )

                LoginTextField(
                    modifier = Modifier.padding(top = 12.dp),
                    placeholder = stringResource(R.string.placeholder_nickname),
                    input = nickname,
                    onTextChange = { newValue ->
                        if (newValue.length <= 10) nickname = newValue
                        enable = newValue.isNotEmpty()
                    },
                )
            }

            DisableBottomFullWidthButton(
                enable = enable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                text = stringResource(R.string.confirm)
            ) {
                navController.navigate(route = Routes.Login)
            }

        }
    }
}

@Preview
@Composable
private fun SignupNicknameScreenPreview() {
    val navController = rememberNavController()

    SignupNicknameScreen(navController)
}