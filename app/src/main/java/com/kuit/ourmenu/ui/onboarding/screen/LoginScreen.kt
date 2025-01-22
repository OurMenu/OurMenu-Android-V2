package com.kuit.ourmenu.ui.onboarding.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.onboarding.component.BottomFullWidthBorderButton
import com.kuit.ourmenu.ui.onboarding.component.LoginTextField
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isChecked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.height(48.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.ourmenu),
                        style = ourMenuTypography().pretendard_700_32,
                        color = Primary500Main,
                    )
                }

                Spacer(modifier = Modifier.height(56.dp))

                LoginTextField(
                    placeholder = stringResource(R.string.email),
                    input = email,
                    onTextChange = { email = it },
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoginTextField(
                    placeholder = stringResource(R.string.password),
                    input = password,
                    onTextChange = { password = it },
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked = it },
                            modifier =
                                Modifier
                                    .border(1.dp, Neutral300, RoundedCornerShape(4.dp))
                                    .size(24.dp),
                            colors =
                                CheckboxDefaults.colors(
                                    checkmarkColor = NeutralWhite,
                                    checkedColor = Primary500Main,
                                    uncheckedColor = Neutral100,
                                ),
                        )

                        Text(
                            text = stringResource(R.string.see_password),
                            style = ourMenuTypography().pretendard_500_14,
                            color = Neutral500,
                            modifier = Modifier.padding(start = 8.dp),
                        )
                    }

                    Text(
                        text = stringResource(R.string.forgot_password),
                        style = ourMenuTypography().pretendard_500_12,
                        color = Neutral500,
                        modifier = Modifier.clickable {
                            // TODO: 비밀번호 찾기 동작 구현
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                BottomFullWidthButton(Primary500Main, NeutralWhite, stringResource(R.string.login)) {
                    // TODO: onClick 작성
                }

                Spacer(modifier = Modifier.height(104.dp))

                Text(
                    text = stringResource(R.string.have_account),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                )

                Spacer(modifier = Modifier.height(24.dp))

                BottomFullWidthBorderButton {
                    // TODO: onClick 작성
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
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
