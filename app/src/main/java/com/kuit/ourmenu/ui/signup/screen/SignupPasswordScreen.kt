package com.kuit.ourmenu.ui.signup.screen

import androidx.compose.animation.core.Animatable
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.DisableBottomFullWidthButton
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.onboarding.component.LoginTextField
import com.kuit.ourmenu.ui.onboarding.component.OnboardingTopAppBar
import com.kuit.ourmenu.ui.onboarding.viewmodel.SignupViewModel
import com.kuit.ourmenu.ui.signup.model.PasswordState
import com.kuit.ourmenu.ui.signup.model.checkPassword
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.AnimationUtil.shakeAnimation
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputField
import com.kuit.ourmenu.utils.AnimationUtil.shakeErrorInputFieldWithFocus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SignupPasswordScreen(
    navigateToMealTime: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val password by viewModel.password.collectAsStateWithLifecycle()
    val confirmPassword by viewModel.confirmPassword.collectAsStateWithLifecycle()
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    // 모든 입력 칸이 채워졌는지 확인
    val isConfirmButtonEnabled = password.isNotEmpty() && confirmPassword.isNotEmpty()

    val focusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }
    var passwordState: PasswordState by remember { mutableStateOf(PasswordState.Default) }
    val shakingModifier = Modifier.offset { IntOffset(shakeOffset.value.roundToInt(), 0) }

    LaunchedEffect(passwordState) {
        when (passwordState) {
            PasswordState.NotMeetCondition -> {
                shakeErrorInputFieldWithFocus(
                    shakeOffset = shakeOffset,
                    focusRequester = focusRequester,
                    message = "비밀번호 조건을 다시 확인해주세요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }

            PasswordState.DifferentPassword -> {
                shakeErrorInputField(
                    shakeOffset = shakeOffset,
                    message = "비밀번호가 일치하지 않아요.",
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
                scope.launch {
                    delay(800)
                    passwordState = PasswordState.Default
                }
            }

            PasswordState.Valid -> {
                navigateToMealTime()
            }

            else -> {}
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = {
            OnboardingTopAppBar(onBackClick = navigateBack)
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
                    text = stringResource(R.string.enter_password),
                    style = ourMenuTypography().pretendard_600_24,
                    color = Neutral900,
                    modifier = Modifier.padding(top = 92.dp),
                )

                Text(
                    text = stringResource(R.string.password_hint),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 4.dp),
                )

                Spacer(modifier = Modifier.height(12.dp))

                LoginTextField(
                    error = when (passwordState) {
                        PasswordState.NotMeetCondition, PasswordState.DifferentPassword -> true
                        else -> false
                    },
                    modifier = when (passwordState) {
                        PasswordState.NotMeetCondition, PasswordState.DifferentPassword ->
                            shakingModifier.focusRequester(focusRequester)

                        else -> Modifier.focusRequester(focusRequester)
                    },
                    placeholder = stringResource(R.string.password_placeholder),
                    input = password,
                    onTextChange = { viewModel.updatePassword(it) },
                    visualTransformation =
                    if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                LoginTextField(
                    error = when (passwordState) {
                        PasswordState.DifferentPassword -> true
                        else -> false
                    },
                    modifier = when (passwordState) {
                        PasswordState.NotMeetCondition, PasswordState.DifferentPassword ->
                            shakingModifier

                        else -> Modifier
                    },
                    placeholder = stringResource(R.string.confirm_password_placeholder),
                    input = confirmPassword,
                    onTextChange = { viewModel.updateConfirmPassword(it) },
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
                    DisableBottomFullWidthButton(
                        enable = isConfirmButtonEnabled,
                        text = stringResource(R.string.confirm),
                        onClick = {
                            passwordState = checkPassword(
                                password = password,
                                confirmPassword = confirmPassword,
                            )
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
    SignupPasswordScreen(
        navigateToMealTime = {},
        navigateBack = {}
    )
}