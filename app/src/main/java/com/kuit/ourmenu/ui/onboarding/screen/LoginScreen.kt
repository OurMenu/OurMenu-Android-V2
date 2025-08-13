package com.kuit.ourmenu.ui.onboarding.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.onboarding.component.BottomFullWidthBorderButton
import com.kuit.ourmenu.ui.common.LoginTextField
import com.kuit.ourmenu.ui.common.topappbar.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.model.LoginUiState
import com.kuit.ourmenu.ui.onboarding.state.LoginState
import com.kuit.ourmenu.ui.onboarding.viewmodel.LoginViewModel
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputFieldWithFocus
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateBack: () -> Unit,
    navigateToSignupEmail: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    LaunchedEffect(uiState.loginState) {
        when (uiState.loginState) {
            is LoginState.Success -> navigateToHome()

            is LoginState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = uiState.error ?: "",
                    )
                }

            }

            is LoginState.NotFoundUser -> {
                shakeErrorInputFieldWithFocus(
                    shakeOffset = shakeOffset,
                    focusRequester = emailFocusRequester,
                    message = "존재하지 않는 이메일입니다.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }

            is LoginState.DifferentPassword -> {
                shakeErrorInputFieldWithFocus(
                    shakeOffset = shakeOffset,
                    focusRequester = passwordFocusRequester,
                    message = "비밀번호가 일치하지 않아요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )

            }


            else -> {}
        }
    }

    LoginScreen(
        navigateBack = navigateBack,
        navigateToSignupEmail = navigateToSignupEmail,
        shakeOffset = shakeOffset,
        uiState = uiState,
        emailFocusRequester = emailFocusRequester,
        passwordFocusRequester = passwordFocusRequester,
        updatePasswordVisible = viewModel::updatePasswordVisible,
        signInWithEmail = viewModel::signInWithEmail,
        updateEmail = viewModel::updateEmail,
        updatePassword = viewModel::updatePassword,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 44.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        OurSnackbarHost(
            hostState = snackbarHostState
        )
    }


}

@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    navigateToSignupEmail: () -> Unit,
    emailFocusRequester: FocusRequester,
    passwordFocusRequester: FocusRequester,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    uiState: LoginUiState,
    signInWithEmail: () -> Unit,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    updatePasswordVisible: (Boolean) -> Unit,
) {
    val shakingModifier = Modifier.offset { IntOffset(shakeOffset.value.roundToInt(), 0) }

    Scaffold(
        topBar = {
            OnboardingTopAppBar(
                onBackClick = navigateBack,
            )
        },
        content = { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp, 48.dp),
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
                    modifier = when (uiState.loginState) {
                        LoginState.NotFoundUser -> shakingModifier.focusRequester(
                            emailFocusRequester
                        )

                        else -> Modifier
                    },
                    error = uiState.loginState == LoginState.NotFoundUser,
                    placeholder = stringResource(R.string.email),
                    input = uiState.email,
                    onTextChange = { updateEmail(it) },
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoginTextField(
                    modifier = when (uiState.loginState) {
                        LoginState.DifferentPassword -> shakingModifier.focusRequester(
                            passwordFocusRequester
                        )

                        else -> Modifier
                    },
                    error = uiState.loginState == LoginState.DifferentPassword,
                    placeholder = stringResource(R.string.password),
                    input = uiState.password,
                    onTextChange = { updatePassword(it) },
                    visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = uiState.isPasswordVisible,
                            onCheckedChange = { updatePasswordVisible(it) },
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
                        modifier =
                            Modifier.clickable {
                                // TODO: 비밀번호 찾기 동작 구현
                            },
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                BottomFullWidthButton(
                    modifier = Modifier,
                    containerColor = Primary500Main,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.login),
                ) { signInWithEmail() }

                Spacer(modifier = Modifier.height(104.dp))

                Text(
                    text = stringResource(R.string.have_account),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                )

                Spacer(modifier = Modifier.height(24.dp))

                BottomFullWidthBorderButton {
                    navigateToSignupEmail()
                }
            }

            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 65.5.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.ourmenu),
                        style = ourMenuTypography().pretendard_700_12,
                        color = Primary500Main,
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(R.string.copy_right),
                        style = ourMenuTypography().pretendard_400_12.copy(
                            fontSize = 10.sp
                        ),
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
    LoginScreen(
        navigateBack = {},
        navigateToSignupEmail = {},
        shakeOffset = remember { Animatable(0f) },
        uiState = LoginUiState(),
        updatePasswordVisible = { },
        signInWithEmail = { },
        updateEmail = { },
        updatePassword = { },
        emailFocusRequester = FocusRequester(),
        passwordFocusRequester = FocusRequester(),
    )
}
