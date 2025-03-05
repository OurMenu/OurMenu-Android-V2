package com.kuit.ourmenu.ui.onboarding.screen.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.onboarding.component.LoginTextField
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SignupPasswordScreen(
    navController: NavController
) {
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    // 모든 입력 칸이 채워졌는지 확인
    val isConfirmButtonEnabled = password.isNotEmpty() && confirmPassword.isNotEmpty()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            OnboardingTopAppBar()
        },
        content = { innerPadding ->
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
            ) {
                Text(
                    text = stringResource(R.string.enter_password),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier.padding(top = 104.dp),
                )

                Text(
                    text = stringResource(R.string.password_hint),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 4.dp),
                )

                Spacer(modifier = Modifier.height(12.dp))

                LoginTextField(
                    placeholder = stringResource(R.string.password_placeholder),
                    input = password,
                    onTextChange = { password = it },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoginTextField(
                    placeholder = stringResource(R.string.confirm_password_placeholder),
                    input = confirmPassword,
                    onTextChange = { confirmPassword = it },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = isPasswordVisible,
                        onCheckedChange = { isPasswordVisible = it },
                        modifier =
                        Modifier
                            .size(24.dp),
                        colors =
                        CheckboxDefaults.colors(
                            checkmarkColor = NeutralWhite,
                            checkedColor = Primary500Main,
                            uncheckedColor = Neutral300,
                        ),
                    )

                    Text(
                        text = stringResource(R.string.see_password),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral500,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        },
        bottomBar = {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                ) {
                    DisableBottomFullWidthButton(
                        enable = isConfirmButtonEnabled,
                        text = stringResource(R.string.confirm),
                        onClick = {
                        },
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupPasswordScreenPreview() {
    val navController = rememberNavController()
    SignupPasswordScreen(navController)
}
