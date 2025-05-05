package com.kuit.ourmenu.ui.signup.screen

import androidx.compose.animation.core.Animatable
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
import androidx.compose.material3.SnackbarDuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.component.VerifyCodeTextField
import com.kuit.ourmenu.ui.signup.model.SignupState
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.AnimationUtil.shakeAnimation
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputField
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SignupVerifyScreen(
    navigateToPassword: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val focusRequesters = List(6) { FocusRequester() }
    val codes by viewModel.codes.collectAsStateWithLifecycle()

    // 모든 입력 칸이 채워졌는지 확인
    val isConfirmButtonEnabled = codes.all { it.isNotEmpty() }
    val verifyState by viewModel.verifyState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }
    val shakingModifier = Modifier.offset { IntOffset(shakeOffset.value.roundToInt(), 0) }


    LaunchedEffect(verifyState) {
        when (verifyState) {
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
                            modifier = when (verifyState) {
                                SignupState.Error -> shakingModifier
                                else -> Modifier
                            }.then(
                                if (i == 0 && codes[i].isEmpty()) {
                                    Modifier.focusRequester(focusRequesters[i])
                                } else {
                                    Modifier
                                }
                            ),
                            error = when (verifyState) {
                                SignupState.Error -> true
                                else -> false
                            },
                            input = codes[i],
                            onTextChange = { newText ->
                                if (newText.length <= 1) {
                                    viewModel.updateCode(i, newText) // Compose에서 상태 변경 감지
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 44.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                OurSnackbarHost(
                    hostState = snackbarHostState
                )
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
                            viewModel.sendEmail()
                        },
                )

                DisableBottomFullWidthButton(
                    enable = isConfirmButtonEnabled,
                    text = stringResource(R.string.confirm),
                    onClick = { viewModel.verifyCode() },
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SignupVerifyScreenPreview() {
    SignupVerifyScreen(
        navigateToPassword = {},
        navigateBack = {}
    )
}