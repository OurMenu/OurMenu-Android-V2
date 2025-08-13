package com.kuit.ourmenu.ui.my.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.LoginTextField
import com.kuit.ourmenu.ui.common.OurSnackbarHost
import com.kuit.ourmenu.ui.common.model.PasswordState
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlin.math.roundToInt

@Composable
fun MyNewPasswordModal(
    onDismiss: () -> Unit = {},
    focusRequester: FocusRequester,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    passwordState: PasswordState = PasswordState.Default,
    snackbarHostState: SnackbarHostState,
    onConfirm: () -> Unit = {},
    passwordConfirmEnabled: Boolean = true,
    newPassword: String = "",
    confirmNewPassword: String = "",
    isPasswordVisible: Boolean = false,
    updateNewPassWordChange: (String) -> Unit = {},
    updateConfirmPasswordChange: (String) -> Unit = {},
    updatePasswordVisibilityChange: () -> Unit = {},
) {
    MyNewPasswordModal(
        onDismiss = onDismiss,
        onConfirmClick = { onConfirm() },
        focusRequester = focusRequester,
        snackbarHostState = snackbarHostState,
        newPassword = newPassword,
        passwordState = passwordState,
        confirmNewPassword = confirmNewPassword,
        shakeOffset = shakeOffset,
        isPasswordVisible = isPasswordVisible,
        enable = passwordConfirmEnabled,
        updateNewPassWordChange = { updateNewPassWordChange(it) },
        updateConfirmPasswordChange = { updateConfirmPasswordChange(it) },
        updatePasswordVisibilityChange = { updatePasswordVisibilityChange() }
    )
}

@Composable
private fun MyNewPasswordModal(
    onDismiss: () -> Unit,
    onConfirmClick: () -> Unit,
    newPassword: String,
    snackbarHostState: SnackbarHostState,
    passwordState: PasswordState,
    focusRequester: FocusRequester,
    confirmNewPassword: String,
    shakeOffset: Animatable<Float, AnimationVector1D>,
    isPasswordVisible: Boolean,
    enable: Boolean,
    updateNewPassWordChange: (String) -> Unit,
    updateConfirmPasswordChange: (String) -> Unit,
    updatePasswordVisibilityChange: () -> Unit,
) {
    val shakingModifier = Modifier.offset { IntOffset(shakeOffset.value.roundToInt(), 0) }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(NeutralWhite, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp)
                    .width(288.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close_24_n400),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDismiss() }
                        .size(24.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.enter_new_password),
                    style = ourMenuTypography().pretendard_700_18,
                    color = Neutral900,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.password_hint),
                    style = ourMenuTypography().pretendard_500_14,
                    color = Neutral500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
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
                    placeholder = stringResource(R.string.new_password),
                    input = newPassword,
                    onTextChange = { updateNewPassWordChange(it) },
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
                    placeholder = stringResource(R.string.confirm_new_password),
                    input = confirmNewPassword,
                    onTextChange = { updateConfirmPasswordChange(it) },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = isPasswordVisible,
                        onCheckedChange = { updatePasswordVisibilityChange() },
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

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onConfirmClick()
                    },
                    enabled = enable,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500Main,
                        contentColor = NeutralWhite
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.confirm),
                        style = ourMenuTypography().pretendard_700_18,
                        color = NeutralWhite
                    )
                }
            }
            OurSnackbarHost(
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 100.dp),
                hostState = snackbarHostState
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyNewPasswordModalPreview() {
    MyNewPasswordModal(
        onDismiss = {},
        focusRequester = FocusRequester(),
        shakeOffset = remember { Animatable(0f) },
        snackbarHostState = SnackbarHostState(),
    )
}