package com.kuit.ourmenu.ui.signup.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.topappbar.OnboardingTopAppBar
import com.kuit.ourmenu.ui.signup.component.VerifyCodeTextField
import com.kuit.ourmenu.ui.signup.model.SignupState
import com.kuit.ourmenu.ui.signup.uistate.SignupUiState
import com.kuit.ourmenu.ui.signup.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputField
import kotlin.math.roundToInt

@Composable
fun SignupVerifyRoute(
    navigateToPassword: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf { uiState.codes.all { it.isNotEmpty() } }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(uiState.verifyState) {
        when (uiState.verifyState) {
            is SignupState.Success ->
                navigateToPassword()

            is SignupState.Error -> {
                shakeErrorInputField(
                    shakeOffset = shakeOffset,
                    message = "인증 코드가 일치하지 않습니다.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }

            else -> {}
        }
    }

    SignupVerifyScreen(
        navigateBack = navigateBack,
        enabled = enabled,
        uiState = uiState,
        shakeOffset = shakeOffset,
        sendEmail = viewModel::sendEmail,
        verifyCode = viewModel::verifyCode,
        updateCode = viewModel::updateCode
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
fun SignupVerifyScreen(
    navigateBack: () -> Unit,
    enabled: Boolean,
    uiState: SignupUiState,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    sendEmail: () -> Unit,
    verifyCode: () -> Unit,
    updateCode: (Int, String) -> Unit,
) {
    val focusRequesters = List(6) { FocusRequester() }
    val shakingModifier = Modifier.offset { IntOffset(shakeOffset.value.roundToInt(), 0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            OnboardingTopAppBar(
                onBackClick = navigateBack
            )
        },
        content = { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 20.dp),
            ) {
                Text(
                    text = stringResource(R.string.sent_mail),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier.padding(top = 92.dp),
                )

                Row {
                    Text(
                        text = stringResource(R.string.in_5_mins),
                        style = ourMenuTypography().pretendard_700_14,
                        color = Primary500Main,
                        modifier = Modifier.padding(top = 4.dp),
                    )

                    Text(
                        text = stringResource(R.string.enter_code),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral500,
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp),
                    )
                }

                Spacer(Modifier.height(12.dp))

                // TODO: 에러 처리하기
                Row {
                    for (i in 0 until 6) {
                        VerifyCodeTextField(
                            modifier = when (uiState.verifyState) {
                                SignupState.Error -> shakingModifier
                                else -> Modifier
                            }.then(
                                if (i == 0 && uiState.codes[i].isEmpty()) {
                                    Modifier.focusRequester(focusRequesters[i])
                                } else {
                                    Modifier
                                }
                            ),
                            error = when (uiState.verifyState) {
                                SignupState.Error -> true
                                else -> false
                            },
                            input = uiState.codes[i],
                            onTextChange = { newText ->
                                if (newText.length <= 1) {
                                    updateCode(i, newText) // Compose에서 상태 변경 감지
                                }
                            },
                            onNext = {
                                if (i < 5) {
                                    focusRequesters[i + 1].requestFocus()
                                }
                            },
                            onPrevious = {
                                if (i > 0) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                            },
                            focusRequester = focusRequesters[i],
                        )
                        if (i < 5) {
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                    }
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
                    HorizontalDivider(
                        color = Neutral300,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                    )

                    Box(
                        contentAlignment = Alignment.Center, // 내용 중앙 정렬
                        modifier =
                            Modifier
                                .background(NeutralWhite)
                                .height(36.dp)
                                .width(124.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.didnt_receive),
                            style = ourMenuTypography().pretendard_500_12,
                            color = Neutral500,
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.resend),
                    style = ourMenuTypography().pretendard_600_14,
                    color = Primary500Main,
                    modifier = Modifier
                        .padding(bottom = 36.dp)
                        .clickable {
                            sendEmail()
                        },
                )

                DisableBottomFullWidthButton(
                    enable = enabled,
                    text = stringResource(R.string.confirm),
                    onClick = { verifyCode() },
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupVerifyScreenPreview() {
    SignupVerifyScreen(
        navigateBack = { },
        enabled = true,
        uiState = SignupUiState(),
        shakeOffset = remember { Animatable(0f) },
        sendEmail = { },
        verifyCode = { },
        updateCode = { _, _ -> }
    )
}